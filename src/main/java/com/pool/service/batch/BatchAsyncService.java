package com.pool.service.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.pool.modal.CustomJobParameter;

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
	
	@Qualifier("flatFileJob")
	@Autowired
	private Job flatFileJob;

	@Async
	public void asyncBatchProsessor(String jobname,List<CustomJobParameter> customJobParameters) {
		try {
			Map<String, JobParameter> param=new HashMap<>();
			param.put("Time", new JobParameter(new Date()));
			customJobParameters.forEach(customJobParameter->param.put(customJobParameter.getParamKey(), new JobParameter(customJobParameter.getParamValue())));
			JobParameters jobParameters=new JobParameters(param);
			JobExecution jobExecution=null;
			if(jobname.equals("firstJob")) {
				jobExecution=jobLauncher.run(firstJob, jobParameters);
			}else if(jobname.equals("secondJob")) {
				jobExecution=jobLauncher.run(secondJob, jobParameters);
			}else if(jobname.equals("flatFileJob")) {
				jobLauncher.run(flatFileJob, jobParameters);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
