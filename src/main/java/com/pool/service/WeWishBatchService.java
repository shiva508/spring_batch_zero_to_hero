package com.pool.service;

import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.pool.configuration.batch.WeWishBatchConfiguration;
import com.pool.configuration.batch.WeWishItemReader;
import com.pool.domin.WeWish;

@Component
public class WeWishBatchService {
	@Autowired
	private WeWishBatchConfiguration weWishBatchConfiguration;
	
	@Autowired
	private JobLauncher jobLauncher;

	 

	public void testBatch(List<WeWish> weWishs) {
		WeWishItemReader<WeWish> userItemReader;
		userItemReader=new WeWishItemReader<WeWish>();
		userItemReader.setReaderItems(weWishs);
		weWishBatchConfiguration.setReader(userItemReader);
		try {
			JobExecution jobExecution=jobLauncher.run(weWishBatchConfiguration.weWishJob(), new JobParametersBuilder()
					.addLong("uniqueness", System.nanoTime()).toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
