package com.pool.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.service.batch.BatchAsyncService;

@RestController
@RequestMapping("/api/job/")
public class BatchJobController {

	@Autowired
	private BatchAsyncService batchAsyncService;
	
	@GetMapping("/start/{jobname}")
	public ResponseEntity<?> batchJobTrigger(@PathVariable("jobname") String jobname){
		try {
			batchAsyncService.asyncBatchProsessor(jobname);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ResponseEntity<>(jobname, HttpStatus.OK);
	}
}
