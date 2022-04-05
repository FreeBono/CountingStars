package com.ssafy.cstars.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cstars.domain.entity.Alarm;
import com.ssafy.cstars.domain.entity.QAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AlarmRepositorySupport {


    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QAlarm qAlarm = QAlarm.alarm;

    public Page<Alarm> findByReceiver(Pageable pageable, String receiver){
        QueryResults<Alarm> alarms = jpaQueryFactory
                .select(qAlarm)
                .from(qAlarm)
                .where(qAlarm.receiver.eq(receiver))
                .orderBy(qAlarm.registerDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();


        if(alarms == null) return Page.empty();

        return new PageImpl<Alarm>(alarms.getResults(), pageable, alarms.getTotal());

    }




}
