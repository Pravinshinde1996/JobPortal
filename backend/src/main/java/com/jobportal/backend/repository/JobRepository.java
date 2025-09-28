package com.jobportal.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.backend.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	Optional<Job> findByCanonicalHash(String canonicalHash);
}
