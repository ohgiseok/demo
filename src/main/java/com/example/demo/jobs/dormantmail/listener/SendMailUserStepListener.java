package com.example.demo.jobs.dormantmail.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendMailUserStepListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution){
        log.info("before step");
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution){
        log.info("after step");
    }
}
