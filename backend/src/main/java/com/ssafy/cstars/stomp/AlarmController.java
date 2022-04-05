package com.ssafy.cstars.stomp;

import com.ssafy.cstars.api.response.AlarmListRes;
import com.ssafy.cstars.api.response.AlarmRes;
import com.ssafy.cstars.domain.entity.Alarm;
import com.ssafy.cstars.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        simpMessageSendingOperations.convertAndSend("/sub/channel/"+alarm.getReceiver(), alarm);

    }

    @GetMapping("/{receiver}")
    public ResponseEntity<Page<AlarmRes>> getBrandList(@PathVariable(name = "receiver") String receiver, @PageableDefault(page = 0, size = 10) Pageable pageable){

        Page<Alarm> alarms = alarmService.GetAlarmList(pageable, receiver);

        if(alarms != null){
            return ResponseEntity.status(200).body(AlarmListRes.of(alarms));
        }else{
            return ResponseEntity.status(500).body(null);
        }

    }


}
