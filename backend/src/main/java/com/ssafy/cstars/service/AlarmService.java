package com.ssafy.cstars.service;


import com.ssafy.cstars.domain.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmService {


    void createAlarm(Alarm alarm);
    Page<Alarm> GetAlarmList(Pageable pageable, String receiver);

    Long modifyAlarmStatus(String receiver);
}
