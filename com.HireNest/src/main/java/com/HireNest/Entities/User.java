package com.HireNest.Entities;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
    private String fullName;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    private String mobile;
    private String workStatus;
    private String currentCity;
    private boolean terms;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Applications> application;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String fullName, String email, String password, String mobile, String workStatus,
			String currentCity, boolean terms, List<Applications> application) {
		super();
		Id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.workStatus = workStatus;
		this.currentCity = currentCity;
		this.terms = terms;
		this.application = application;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public boolean isTerms() {
		return terms;
	}

	public void setTerms(boolean terms) {
		this.terms = terms;
	}

	public List<Applications> getApplication() {
		return application;
	}

	public void setApplication(List<Applications> application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + ", workStatus=" + workStatus + ", currentCity=" + currentCity + ", terms=" + terms
				+ ", application=" + application + "]";
	}
    
}
