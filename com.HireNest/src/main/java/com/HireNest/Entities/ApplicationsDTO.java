package com.HireNest.Entities;

import java.time.LocalDateTime;

public class ApplicationsDTO {
	
	
	private Long id;

	private String name;

	private LocalDateTime applicationDate;

	private String status;

	private String email;
	
	private String jobTitle;

	public ApplicationsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationsDTO(Long id, String name, LocalDateTime applicationDate, String status, String email) {
		super();
		this.id = id;
		this.name = name;
		this.applicationDate = applicationDate;
		this.status = status;
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}