package com.jobportal.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.backend.entity.Job;
import com.jobportal.backend.service.JobService;

@RestController
@RequestMapping("/internal")
public class JobController {
	private final JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping("/jobs")
	public ResponseEntity<?> ingestJob(@RequestBody Job job) {
		if (job.getTitle() == null || job.getTitle().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Missing title");
		}
		// optionally parse dates from strings in DTO stage; here we assume job.postDate
		// and job.deadline are set
		Job saved = jobService.upsert(job);
		return ResponseEntity.ok(saved);
	}

}
