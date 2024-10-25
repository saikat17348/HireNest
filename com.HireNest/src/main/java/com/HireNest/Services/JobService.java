package com.HireNest.Services;

import java.util.List;
import java.util.Optional;

import com.HireNest.Entities.Jobs;

public interface JobService {
	
	void jobPost(Jobs job);
	
	List<Jobs> jobSearch(String title, String location, String experienceLevel, String company);
	
	Jobs searchJobById(Long id);
	
	// using this for getting the list of jobs for the employee so that the each employee can see their perticular jobs. 
	List<Jobs> listOfJobsByEmpId(Long empId);

	Optional<Jobs> findJobById(Long id);
	
	Optional<Jobs> findById(Long id);

	void deleteById(Long id);
}
