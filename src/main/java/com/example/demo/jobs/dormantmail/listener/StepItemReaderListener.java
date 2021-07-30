package com.example.demo.jobs.dormantmail.listener;

import com.example.demo.jobs.dormantmail.model.domain.DormantNoti;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepItemReaderListener {

    @BeforeRead
    public void beforeRead(){
        log.info("before read");
    }

    @AfterRead
    public void afterRead(DormantNoti item){
        log.info("after read : " + item.toString());
    }

    @OnReadError
    public void onReadError(Exception ex){
        log.info("on read error");
    }


}
