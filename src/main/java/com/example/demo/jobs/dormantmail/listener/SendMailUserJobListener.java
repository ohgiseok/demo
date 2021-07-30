package com.example.demo.jobs.dormantmail.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendMailUserJobListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution){
        log.info("before job");
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution){
        log.info("after job");
    }
}
