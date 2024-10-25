package com.HireNest.Entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.OneToMany;

public class JobsDTO {
	private Long id;

	private String title;
	private String location;
	private String experienceLevel;
	private String company;
	private String description;
	private String requirements;
	private String salary;
	private String employementType;
	private LocalDate postingDate;
	
	private int applicationcount;
	@OneToMany
	private List<ApplicationsDTO> applicationsDTO;

	public JobsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobsDTO(Long id, String title, String location, String experienceLevel, String company, String description,
			String requirements, String salary, String employementType, LocalDate postingDate,
			List<ApplicationsDTO> applicationsDTO,int applicationcount) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.experienceLevel = experienceLevel;
		this.company = company;
		this.description = description;
		this.requirements = requirements;
		this.salary = salary;
		this.employementType = employementType;
		this.postingDate = postingDate;
		this.applicationsDTO = applicationsDTO;
		this.applicationcount = applicationcount;
	}

	public Long getId() {
		return id;
	}

	public int getApplicationcount() {
		return applicationcount;
	}

	public void setApplicationcount(int applicationcount) {
		this.applicationcount = applicationcount;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getEmployementType() {
		return employementType;
	}

	public void setEmployementType(String employementType) {
		this.employementType = employementType;
	}

	public LocalDate getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(LocalDate postingDate) {
		this.postingDate = postingDate;
	}

	public List<ApplicationsDTO> getApplicationsDTO() {
		return applicationsDTO;
	}

	public void setApplicationsDTO(List<ApplicationsDTO> applicationsDTO) {
		this.applicationsDTO = applicationsDTO;
	}

	@Override
	public String toString() {
		return "JobsDTO [id=" + id + ", title=" + title + ", location=" + location + ", experienceLevel="
				+ experienceLevel + ", company=" + company + ", description=" + description + ", requirements="
				+ requirements + ", salary=" + salary + ", employementType=" + employementType + ", postingDate="
				+ postingDate + ", applicationsDTO=" + applicationsDTO + "]";
	}
	
	
	
	
}