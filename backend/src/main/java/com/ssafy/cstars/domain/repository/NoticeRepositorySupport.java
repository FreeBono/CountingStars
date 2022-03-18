package com.ssafy.cstars.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cstars.domain.entity.Notice;
import com.ssafy.cstars.domain.entity.QNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QNotice qNotice = QNotice.notice;

    public Page<Notice> findAll(Pageable pageable) {
        QueryResults<Notice> notices = jpaQueryFactory
                .select(qNotice)
                .from(qNotice)
                .orderBy(qNotice.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        if (notices == null) return Page.empty();

        return new PageImpl<Notice>(notices.getResults(), pageable, notices.getTotal());
    }
}
