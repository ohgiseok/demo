package com.example.demo.jobs.dormantmail.listener;

import com.example.demo.jobs.dormantmail.model.domain.DormantNoti;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepItemProcessListener {

    @BeforeProcess
    public void beforeProcess(DormantNoti item){
        log.info("before Process");
    }

    @AfterProcess
    public void afterProcess(DormantNoti item, DormantNoti result){
        log.info("after Process : in - "+ item.toString() + " out - "+result.toString());
    }

    @OnProcessError
    public void onProcessError(DormantNoti item, Exception e){
        log.info("on Process error");
    }

}
