package com.example.demo.jobs.dormantmail.config;

import com.example.demo.jobs.dormantmail.listener.SendMailUserJobListener;
import com.example.demo.jobs.dormantmail.listener.SendMailUserStepListener;
import com.example.demo.jobs.dormantmail.model.domain.DormantNoti;
import com.example.demo.jobs.dormantmail.model.domain.enums.NotiState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Configuration
public class SendMailUserJobConfig {

//    private DormantNotiDao dormantNotiDao;

    @Bean
    public Job sendMailUserJob(JobBuilderFactory jobBuilderFactory, SendMailUserJobListener sendMailUserJobListener, Step sendMailJobStep){
        return jobBuilderFactory.get("sendMailUserJob")
                .preventRestart()
                .listener(sendMailUserJobListener)
                .start(sendMailJobStep)
                .build();
    }


}
