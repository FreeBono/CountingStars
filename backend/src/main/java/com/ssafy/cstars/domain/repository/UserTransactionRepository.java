package com.ssafy.cstars.domain.repository;

import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.domain.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {

    List<UserTransaction> findByUserId(Long user);
}
