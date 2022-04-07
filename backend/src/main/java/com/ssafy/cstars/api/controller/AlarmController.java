package com.ssafy.cstars.api.controller;

import com.ssafy.cstars.api.response.AlarmListRes;
import com.ssafy.cstars.api.response.AlarmRes;
import com.ssafy.cstars.api.response.BaseResponseBody;
import com.ssafy.cstars.domain.entity.Alarm;
import com.ssafy.cstars.service.AlarmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/alarm")
@RequiredArgsConstructor
public class AlarmController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    AlarmService alarmService;

    @MessageMapping("/pubs")
    public void message(Alarm alarm){
        alarm.setRegisterDate();

        alarmService.createAlarm(alarm);

        if(alarm.getReceiver().equals("ROLE_BRAND_ADMIN")){
            System.out.println("/sub/channel/"+alarm.getReceiver()+"/"+alarm.getBrand());
            simpMessageSendingOperations.convertAndSend("/sub/channel/"+alarm.getReceiver()+"/"+alarm.getBrand(), alarm);
        }else {
            simpMessageSendingOperations.convertAndSend("/sub/channel/" + alarm.getReceiver(), alarm);
        }
    }

    @GetMapping("/{receiver}")
    public ResponseEntity<Page<AlarmRes>> GetAlarmList(@PathVariable(name = "receiver") String receiver, @PageableDefault(page = 0, size = 10) Pageable pageable){

        Page<Alarm> alarms = alarmService.GetAlarmList(pageable, receiver);

        if(alarms != null){
            return ResponseEntity.status(200).body(AlarmListRes.of(alarms));
        }else{
            return ResponseEntity.status(500).body(null);
        }

    }


    @GetMapping("/{receiver}/{brand}")
    public ResponseEntity<Page<AlarmRes>> GetBrandAlarmList(@PathVariable(name = "receiver") String receiver,
                                                            @PathVariable(name = "brand") String brand,
                                                            @PageableDefault(page = 0, size = 10) Pageable pageable){

        Page<Alarm> alarms = alarmService.GetBrandAlarmList(pageable, receiver, brand);

        if(alarms != null){
            return ResponseEntity.status(200).body(AlarmListRes.of(alarms));
        }else{
            return ResponseEntity.status(500).body(null);
        }

    }


    @PutMapping("/{receiver}/{check}")
    @ApiOperation(value = "알람 상태 수정 (alarm status)", notes = "<strong>알람 상태</strong>을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> modifyAlarmStatus(@PathVariable(name = "receiver") String receiver,
                                                              @PathVariable(name = "check") int check){

        Long execute = null;

        if(check == 0 ) {
            execute = alarmService.modifyAlarmStatus(receiver);
        }else if(check == 1){
            execute = alarmService.modifyBrandAlarmStatus(receiver);
        }
        int statusCode;

        if(execute != 0){
            statusCode = 200;
        }else {
            statusCode = 500;
        }

        return createResponseEntityToStatusCode(statusCode);
    }

    private ResponseEntity<BaseResponseBody> createResponseEntityToStatusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
            case 401:
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "ACCESS DENIED"));
            default:
                return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }
}
