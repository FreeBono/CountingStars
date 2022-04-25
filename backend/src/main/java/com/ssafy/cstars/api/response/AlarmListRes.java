package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class AlarmListRes {

    public static Page<AlarmRes> of(Page<Alarm> alarmList){
        List<AlarmRes> list = new ArrayList<>();

        Pageable pageable = alarmList.getPageable();
        long total = alarmList.getTotalElements();

        alarmList.forEach(alarm -> {
            list.add(AlarmRes.of(alarm));
        });

        return new PageImpl<AlarmRes>(list, pageable, total);

    }

}
