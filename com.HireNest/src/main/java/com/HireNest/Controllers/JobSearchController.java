package com.HireNest.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HireNest.Application;
import com.HireNest.Entities.Applications;
import com.HireNest.Entities.ApplicationsDTO;
import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.JobsDTO;
import com.HireNest.Services.EmployeeService;
import com.HireNest.Services.JobService;

import jakarta.servlet.http.HttpSession;

@RestController
public class JobSearchController {
	
	@Autowired
	JobService jobservice;
	
//	@Autowired
//	EmployeeService empservice;
	
	
//	@PostMapping("employee/jobs/post")
//	public String postJob(@ModelAttribute Jobs job, HttpSession session, Model model)
//	{
//		// getting the current looged in empID from the session.
//		Long currentLoggedInEmpId =(Long) session.getAttribute("employeeId");
//		if(currentLoggedInEmpId != null)
//		{
//			// getting the current logged in employee object through the empId.
//			EmployerDetails empObject = empservice.getEmpById(currentLoggedInEmpId);
//			
//			// giving the current logged in user to the job entity as which will act as a foreign key in the table at database
//			job.setEmployerdetails(empObject);
//			
//			// setting the job to the employee entity mapping this bidirectional way.
//			empObject.setJobsTotheEmployee(job);
//			
//			// now this will save the job to the job table. 
//			jobservice.jobPost(job);// posting the job which is coming from the view 
//			
//			// getting the list of jobs by the current logged in employee so that it can be displayed to them in the next page
//			List<Jobs> jobs = jobservice.listOfJobsByEmpId(currentLoggedInEmpId);
//			
//			if(jobs != null)
//			{
//			  model.addAttribute("listOfJobsForEachEmployee", jobs);
//			  return "myJobs";
//			}
//			else
//			{
//				return "home";
//			}
//		}
//		else
//		{
//			return "employeeSignUp";
//		}
//	}
	
	
	@PostMapping("/job/search")
	public ResponseEntity<?> jobSearch(@RequestParam(required = false)String title,
			@RequestParam(required = false) String location,
			@RequestParam(required= false) String experienceLevel,
			@RequestParam(required=false) String company)
	{
		List<Jobs> listOfJobs = jobservice.jobSearch(title, location, experienceLevel, company);
		
		List<JobsDTO> joblist = new ArrayList<>();
		
		if(listOfJobs != null)
		{
			for(Jobs job : listOfJobs)
			{
				JobsDTO jobdto = new JobsDTO();
				jobdto.setId(job.getId());
				jobdto.setTitle(job.getTitle());
				jobdto.setLocation(job.getLocation());
				jobdto.setExperienceLevel(job.getExperienceLevel());
				jobdto.setCompany(job.getCompany());
				jobdto.setDescription(job.getDescription());
				jobdto.setRequirements(job.getRequirements());
				jobdto.setSalary(job.getSalary());
				jobdto.setEmployementType(job.getEmployementType());
				jobdto.setPostingDate(job.getPostingDate());
				
				List<ApplicationsDTO> appDTOnewlist = new ArrayList<>();
				
				List<Applications> applicationsfromlistofofjobs = job.getApplications();
				for(Applications app : applicationsfromlistofofjobs)
				{
					ApplicationsDTO appdto = new ApplicationsDTO();
					appdto.setId(app.getId());
					appdto.setName(app.getName());
					appdto.setEmail(app.getName());
					appdto.setStatus(app.getStatus());
					appdto.setApplicationDate(app.getApplicationDate());
					appDTOnewlist.add(appdto);
				}
				
				jobdto.setApplicationsDTO(appDTOnewlist);
				
				
				joblist.add(jobdto);
				
			}
			
			return new ResponseEntity<>(joblist,HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		
        
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session)
	{
		
		session.removeAttribute("userId");
		
		//Long id =(Long)session.getAttribute("userId");
		//System.out.println(id);
		return "loggedout";
	}

}
