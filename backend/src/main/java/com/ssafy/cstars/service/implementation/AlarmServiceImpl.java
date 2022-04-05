package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.domain.entity.Alarm;
import com.ssafy.cstars.domain.repository.AlarmRepository;
import com.ssafy.cstars.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AlarmService")
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmRepository alarmRepository;

    @Override
    public void createAlarm(Alarm alarmrst) {

        Alarm alarm = Alarm.builder()
                .storeAdmin(alarmrst.getStoreAdmin())
                .storeBrand(alarmrst.getStoreBrand())
                .productName(alarmrst.getProductName())
                .registerDate(alarmrst.getRegisterDate())
                .build();

        alarmRepository.save(alarm);

    }


}
