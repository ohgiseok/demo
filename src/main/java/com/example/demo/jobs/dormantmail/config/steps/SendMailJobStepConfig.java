package com.example.demo.jobs.dormantmail.config.steps;

import com.example.demo.jobs.dormantmail.listener.SendMailUserStepListener;
import com.example.demo.jobs.dormantmail.listener.StepItemProcessListener;
import com.example.demo.jobs.dormantmail.listener.StepItemReaderListener;
import com.example.demo.jobs.dormantmail.listener.StepItemWriterListener;
import com.example.demo.jobs.dormantmail.model.domain.DormantNoti;
import com.example.demo.jobs.dormantmail.model.domain.enums.NotiState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
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
public class SendMailJobStepConfig {

    private final static int CHUNK_SIZE =3;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Step sendMailJobStep(StepBuilderFactory stepBuilderFactory ,
                                SendMailUserStepListener sendMailUserStepListener,
                                StepItemReaderListener stepItemReaderListener,
                                StepItemProcessListener stepItemProcessListener,
                                StepItemWriterListener stepItemWriterListener,
                                JpaPagingItemReader<DormantNoti> sendMailUserReader){
        return stepBuilderFactory.get("sendMailJobStep")
                .<DormantNoti, DormantNoti>chunk(CHUNK_SIZE)
                .reader(sendMailUserReader)
                .processor(sendMailUserProcessor())
                .writer(sendMailWriter())
                .listener(sendMailUserStepListener)
//                .listener(stepItemReaderListener)
//                .listener(stepItemProcessListener)
//                .listener(stepItemWriterListener)
                .build();
    }

    @Bean(destroyMethod = "")
    @StepScope
    public JpaPagingItemReader<DormantNoti> sendMailUserReader() {
        JpaPagingItemReader<DormantNoti> jpaPagingItemReader = new JpaPagingItemReader(){
            @Override
            public int getPage(){
                return 0;
            }
        };

        jpaPagingItemReader.setQueryString("select i from DormantNoti as i where i.state = :state");

        Map<String, Object> map = new HashMap<>();
        map.put("state", NotiState.WAIT);

        jpaPagingItemReader.setParameterValues(map);
        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        jpaPagingItemReader.setPageSize(CHUNK_SIZE);
        return jpaPagingItemReader;
    }

    public ItemProcessor<DormantNoti, DormantNoti> sendMailUserProcessor() {
        //return DormantNoti::sendMail();

        return new ItemProcessor<DormantNoti, DormantNoti>() {
            @Override
            public DormantNoti process(DormantNoti dormantNoti) throws Exception{
                LocalDate now = LocalDate.now();
                dormantNoti.setState(NotiState.SUCCESS);
                dormantNoti.setNotiDate(now);
                return dormantNoti;
            }
        };
    }

    public JpaItemWriter<DormantNoti> sendMailWriter(){
        JpaItemWriter<DormantNoti> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
}
