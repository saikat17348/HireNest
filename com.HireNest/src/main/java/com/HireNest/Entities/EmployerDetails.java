package com.HireNest.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class EmployerDetails {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String companyName;
	
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "employerDetails",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	List<Jobs> listOfJobs = new ArrayList<>();
	
	public void setJobsTotheEmployee(Jobs job)
	{
		listOfJobs.add(job);
	}
	
	@OneToMany(mappedBy = "employerDetails",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Applications> listOfApplications = new ArrayList<>();
	
	public void setApplicatonsToTheEmployee(Applications application)
	{
		listOfApplications.add(application);
	}

	public EmployerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployerDetails(Long id, String companyName, String email, String password, List<Jobs> listOfJobs,
			List<Applications> listOfApplications) {
		super();
		Id = id;
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.listOfJobs = listOfJobs;
		this.listOfApplications = listOfApplications;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Jobs> getListOfJobs() {
		return listOfJobs;
	}

	public void setListOfJobs(List<Jobs> listOfJobs) {
		this.listOfJobs = listOfJobs;
	}

	public List<Applications> getListOfApplications() {
		return listOfApplications;
	}

	public void setListOfApplications(List<Applications> listOfApplications) {
		this.listOfApplications = listOfApplications;
	}
}
