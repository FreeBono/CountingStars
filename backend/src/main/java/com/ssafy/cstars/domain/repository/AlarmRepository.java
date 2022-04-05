package com.ssafy.cstars.domain.repository;

import com.ssafy.cstars.domain.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {


}
