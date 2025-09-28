package com.jobportal.backend.service;

import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.jobportal.backend.entity.Job;
import com.jobportal.backend.repository.JobRepository;

public class JobService {
	private final JobRepository repo;

	public JobService(JobRepository repo) {
		this.repo = repo;
	}

	public static String canonicalHash(String title, String org, String deadline) {
		try {
			String s = (title == null ? "" : title.trim().toLowerCase()) + "|"
					+ (org == null ? "" : org.trim().toLowerCase()) + "|" + (deadline == null ? "" : deadline.trim());
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(s.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (byte b : hash)
				sb.append(String.format("%02x", b));
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public Job upsert(Job incoming) {
		String ch = canonicalHash(incoming.getTitle(), incoming.getOrgName(),
				incoming.getDeadline() == null ? "" : incoming.getDeadline().toString());
		Optional<Job> existing = repo.findByCanonicalHash(ch);
		
		if (existing.isPresent()) {
			Job e = existing.get();
			// update fields you want to refresh
			e.setDescription(incoming.getDescription());
			e.setSourceUrl(incoming.getSourceUrl());
			e.setUpdatedAt(java.time.LocalDateTime.now());
			return repo.save(e);
		} else {
			incoming.setCanonicalHash(ch);
			if (incoming.getStatus() == null)
				incoming.setStatus("pending");
			return repo.save(incoming);
		}
	}

}
