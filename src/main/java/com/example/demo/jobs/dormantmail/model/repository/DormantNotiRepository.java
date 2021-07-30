package com.example.demo.jobs.dormantmail.model.repository;

import com.example.demo.jobs.dormantmail.model.domain.DormantNoti;
import com.example.demo.jobs.dormantmail.model.domain.enums.NotiState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormantNotiRepository extends JpaRepository<DormantNoti, Integer> {
    List<DormantNoti> findByState(NotiState notiState);
}
