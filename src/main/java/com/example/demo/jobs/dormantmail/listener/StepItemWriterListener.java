package com.example.demo.jobs.dormantmail.listener;

import com.example.demo.jobs.dormantmail.model.domain.DormantNoti;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StepItemWriterListener {

    @BeforeWrite
    public void beforeWrite(List<? extends DormantNoti> items){
        log.info("before write list size : " + items.size());
    }

    @AfterWrite
    public void afterWrite(List<? extends DormantNoti> items){
        log.info("after write list size : " + items.size());
    }

    @OnWriteError
    public void onWriteError(Exception ex, List<? extends DormantNoti> items){
        log.info("on write error");
    }
}
