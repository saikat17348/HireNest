package com.HireNest.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.Jobs;
import com.HireNest.Repositories.ApplicationsRepository;
import com.HireNest.Repositories.JobsRepository;

import jakarta.transaction.Transactional;

@Service
public class JobImplementation implements JobService{

	@Autowired
	JobsRepository jobrepo;
	
	@Autowired
	ApplicationsRepository applicationsrepo;
	
	public void jobPost(Jobs job) 
	{
		jobrepo.save(job);
	}

	@Override
	public List<Jobs> jobSearch(String title, String location, String experienceLevel, String company) 
	{
		List<Jobs> listOfJobs = new ArrayList<>();
		
		if(title != null && title.isEmpty() == false)
		{
			List<Jobs> jobsByTitle = jobrepo.searchByTitle(title);
			listOfJobs.addAll(jobsByTitle);
		}
		if(location != null && location.isEmpty() == false)
		{
			listOfJobs.addAll(jobrepo.searchByLocation(location));
		}
		if(experienceLevel != null && experienceLevel.isEmpty() == false)
		{
			listOfJobs.addAll(jobrepo.searchByExperienceLevel(experienceLevel));
		}
//		else if(company != null && company.isEmpty() == false)
//		{
//			listOfJobs.addAll(jobrepo.searchByCompanyName(company));
//		}
		
		
		// removing the duplicate elements 
		//Set<Jobs> removingDeblicates = new LinkedHashSet<>(jobsInSortedOrder);
		
		// adding back to list based class to sort using comparator
		  //jobsInSortedOrder.clear();
		  //jobsInSortedOrder.addAll(removingDeblicates);
		
		if(listOfJobs != null)
		{
        JobsSortingHelper jsh = new JobsSortingHelper();
		Collections.sort(listOfJobs, jsh);
		return listOfJobs;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public Jobs searchJobById(Long id) 
	{
	    return jobrepo.getById(id);
	}

	@Override
	public List<Jobs> listOfJobsByEmpId(Long empId) 
	{
	    List<Jobs> listOfJobsPostedByEmployer = jobrepo.findByEmployerDetails_Id(empId);
	    
	    if(listOfJobsPostedByEmployer != null)
	    {
	    	return listOfJobsPostedByEmployer;
	    }
	    else
	    {
	    	return null;
	    }	
	}

	@Override
	public Optional<Jobs> findJobById(Long id) {
		
		Optional<Jobs> jobs = jobrepo.findById(id);
		
		return jobs;
	}

	@Override
	public Optional<Jobs> findById(Long id) {
		
		Optional<Jobs> job = jobrepo.findById(id);
		
		return job;
	}
     
	@Transactional
	@Override
	public void deleteById(Long id) 
	{
		Jobs job = jobrepo.findById(id).orElseThrow(() -> new RuntimeException("job not found"));
		
		//List<Applications> app = job.getApplications();
		 List<Applications> applications = new ArrayList<>(job.getApplications()); // Clone to avoid ConcurrentModificationException
	        for (Applications application : applications) {
	            application.setJobs(null); // Break the reference
	            applicationsrepo.delete(application); // Delete the application
	        }
		
		jobrepo.delete(job);
//		applicationsrepo.deleteByJobs_Id(id);
//		
//        jobrepo.deleteById(id);

	}
}
