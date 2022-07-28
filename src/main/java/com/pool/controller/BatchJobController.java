package com.pool.controller;

import java.util.List;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.modal.CustomJobParameter;
import com.pool.service.batch.BatchAsyncService;

@RestController
@RequestMapping("/api/job/")
public class BatchJobController {

	@Autowired
	private BatchAsyncService batchAsyncService;
	
	@Autowired
	private JobOperator jobOperator;
	
	@PostMapping("/start/{jobname}")
	public ResponseEntity<?> batchJobTrigger(@PathVariable("jobname") String jobname,@RequestBody List<CustomJobParameter> customJobParameters){
		try {
			batchAsyncService.asyncBatchProsessor(jobname,customJobParameters);
		} catch (Exception e) {
		
		}
		return new ResponseEntity<>(jobname, HttpStatus.OK);
	}
	
	@PostMapping("/stop/{executionid}")
	public ResponseEntity<?> batchJobStopper(@PathVariable("executionid") Long executionid){
		try {
			jobOperator.stop(executionid);
		} catch (Exception e) {
		
		}
		return new ResponseEntity<>(executionid, HttpStatus.OK);
	}
}
