package com.pool.service.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BatchAsyncService {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Qualifier("firstJob")
	@Autowired
	private Job firstJob;
	
	@Qualifier("secondJob")
	@Autowired
	private Job secondJob;

	@Async
	public void asyncBatchProsessor(String jobname) {
		try {
			Map<String, JobParameter> param=new HashMap<>();
			param.put("Time", new JobParameter(new Date()));
			JobParameters jobParameters=new JobParameters(param);
			JobExecution jobExecution=null;
			if(jobname.equals("firstJob")) {
				jobExecution=jobLauncher.run(firstJob, jobParameters);
			}else if(jobname.equals("secondJob")) {
				jobExecution=jobLauncher.run(secondJob, jobParameters);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
