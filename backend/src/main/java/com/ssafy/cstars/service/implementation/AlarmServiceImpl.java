package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.domain.entity.Alarm;
import com.ssafy.cstars.domain.repository.AlarmRepository;
import com.ssafy.cstars.domain.repository.AlarmRepositorySupport;
import com.ssafy.cstars.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("AlarmService")
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmRepository alarmRepository;
    @Autowired
    AlarmRepositorySupport alarmRepositorySupport;

    @Override
    public void createAlarm(Alarm alarmrst) {

        Alarm alarm = Alarm.builder()
                .receiver(alarmrst.getReceiver())
                .sender(alarmrst.getSender())
                .productName(alarmrst.getProductName())
                .registerDate(alarmrst.getRegisterDate())
                .build();

        alarmRepository.save(alarm);

    }

    @Override
    public Page<Alarm> GetAlarmList(Pageable pageable, String receiver) {

        Page<Alarm> alarms = alarmRepositorySupport.findByReceiver(pageable, receiver);


        return null;
    }


}
