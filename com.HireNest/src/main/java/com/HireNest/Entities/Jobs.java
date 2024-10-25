package com.HireNest.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Jobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(mappedBy = "jobs", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	List<Applications> applications = new ArrayList<>();

	@ManyToOne
	EmployerDetails employerDetails;
	
	public void setApplicationsToJobs(Applications application)
	{
		applications.add(application);
		application.setJobs(this);
	}
	
	public void removeApplication(Applications application) {
		applications.remove(application);
		application.setJobs(null);
	}

	public Jobs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jobs(Long id, String title, String location, String experienceLevel, String company, String description,
			String requirements, String salary, String employementType, LocalDate postingDate,
			List<Applications> applications, EmployerDetails employerDetails) {
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
		this.applications = applications;
		this.employerDetails = employerDetails;
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

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

	public EmployerDetails getEmployerdetails() {
		return employerDetails;
	}

	public void setEmployerdetails(EmployerDetails employerDetails)
	{
		this.employerDetails = employerDetails;
	}

	@Override
	public String toString() {
		return "Jobs [id=" + id + ", title=" + title + ", location=" + location + ", experienceLevel=" + experienceLevel
				+ ", company=" + company + ", description=" + description + ", requirements=" + requirements
				+ ", salary=" + salary + ", employementType=" + employementType + ", postingDate=" + postingDate
				+ ", applications=" + applications + ", employerDetails=" + employerDetails + "]";
	}
	
	
}
