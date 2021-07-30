package com.example.demo;

import com.example.demo.jobs.dormantmail.model.repository.DormantNotiRepository;
import com.example.demo.jobs.dormantmail.model.domain.enums.NotiState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private DormantNotiRepository dormantNotiRepository;

	@Test
	public void contextLoads() throws Exception{

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		assertEquals(0,dormantNotiRepository.findByState(NotiState.WAIT).size());
	}

}
