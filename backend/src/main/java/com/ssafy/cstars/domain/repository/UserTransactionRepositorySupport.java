package com.ssafy.cstars.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cstars.domain.entity.QUser;
import com.ssafy.cstars.domain.entity.QUserTransaction;
import com.ssafy.cstars.domain.entity.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;

@Repository
public class UserTransactionRepositorySupport {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QUserTransaction qUserTransaction = QUserTransaction.userTransaction;

    public Page<String> rank(Pageable pageable) {
        QueryResults<String> ranks = jpaQueryFactory
                .select(qUser.username)
                .from(qUser, qUserTransaction)
                .where(qUserTransaction.user.eq(qUser))
                .groupBy(qUserTransaction.user)
                .orderBy(qUserTransaction.user.count().desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        if(ranks == null) return Page.empty();

        return new PageImpl<String>(ranks.getResults(), pageable, ranks.getTotal());
    }
}







//    select a.user_id, b.username, count(a.user_id)
//        from user_transaction a
//        inner join users b on (b.id = a.user_id)
//        group by a.user_id
//        order by count(a.user_id) DESC;