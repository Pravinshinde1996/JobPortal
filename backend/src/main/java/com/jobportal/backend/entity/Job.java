package com.jobportal.backend.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs", uniqueConstraints = {
		@UniqueConstraint(name = "uc_jobs_canonical", columnNames = { "canonical_hash" }) })
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 800)
	private String title;

	private String orgName;
	private String category;
	private String location;

	private java.time.LocalDate postDate;
	private java.time.LocalDate deadline;

	@Column(length = 4000)
	private String description;

	private String sourceUrl;
	private String sourceName;
	private String canonicalHash;

	private String status; // pending/active/expired

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public java.time.LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(java.time.LocalDate postDate) {
		this.postDate = postDate;
	}

	public java.time.LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(java.time.LocalDate deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getCanonicalHash() {
		return canonicalHash;
	}

	public void setCanonicalHash(String canonicalHash) {
		this.canonicalHash = canonicalHash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
