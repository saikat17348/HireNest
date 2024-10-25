package com.HireNest.Controllers;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.ApplicationsDTO;
import com.HireNest.Entities.EmployerDetails;
import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.JobsDTO;
import com.HireNest.Services.ApplicationService;
import com.HireNest.Services.EmployeeService;
import com.HireNest.Services.JobService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@Autowired
	JobService jobservice;
	
	@Autowired
	ApplicationService applicationservice;

	@PostMapping("/employee/register")
	public String registerEmployee(@RequestBody EmployerDetails empdetails) {
		boolean status = service.addEmployee(empdetails);
		if (status == false) {
			return "home";
		} else {
			return "employeeLogin";
		}
	}

	@PostMapping("/employee/login")
	public String employeeLogin(@RequestBody EmployerDetails emp, HttpSession session)
    {
		
		EmployerDetails empobject = service.checkEmailAndPassword(emp.getEmail(), emp.getPassword());
		if (empobject != null) {
			
			// Long employeeId = empobject.getId();
			session.setAttribute("empobject", empobject);
			
			return "jobPost";
		} else 
		{
			return "employeeSignUp";
		}
	}
     
	
	@PostMapping("/employee/jobs/post")
	public  ResponseEntity<?> addJob(@RequestBody Jobs job, HttpSession session)
	{
		
		EmployerDetails emp = (EmployerDetails)session.getAttribute("empobject");

		if (emp == null) {
			return null;
		}
		emp.setJobsTotheEmployee(job);

		job.setEmployerdetails(emp);

		jobservice.jobPost(job);

		Long empId = (Long) emp.getId();

		List<Jobs> listOfJobs = jobservice.listOfJobsByEmpId(empId);
		
		//Map<Jobs,Integer> applicationcount = new LinkedHashMap<Jobs,Integer>();
		
		List<JobsDTO> jobsdto = new ArrayList<>();
		
		for(Jobs jobss : listOfJobs)
		{
			int applicationCount = jobss.getApplications().size();
		//	listofapplicationscount.put(jobss, applicationCount);
			
			JobsDTO jobs = new JobsDTO();
			jobs.setId(jobss.getId());
			jobs.setTitle(jobss.getTitle());
			jobs.setLocation(jobss.getLocation());
			jobs.setExperienceLevel(jobss.getExperienceLevel());
			jobs.setCompany(jobss.getCompany());
			jobs.setDescription(jobss.getDescription());
			jobs.setRequirements(jobss.getRequirements());
			jobs.setSalary(jobss.getSalary());
			jobs.setEmployementType(jobs.getEmployementType());
			jobs.setPostingDate(jobss.getPostingDate());
			jobs.setApplicationcount(applicationCount);
			
			jobsdto.add(jobs);
			
		}
		if(jobsdto.isEmpty() == false)
		{
			return new ResponseEntity<>(jobsdto,HttpStatus.OK);
		}
		else
		{
		//System.out.println("else block returning null");
		return null;
		}
		
	}

	// this method is just for the navbar myjob option.
	
	@PostMapping("/myjobs")
	public ResponseEntity<?> myjobs(HttpSession session) 
	{
		
		EmployerDetails emp = (EmployerDetails) session.getAttribute("empobject");
	
		
		if (emp != null) 
		{
	
			
			List<Jobs> listOfJobs = jobservice.listOfJobsByEmpId(emp.getId());
			
	
		//	Map<Jobs,Integer> listofapplicationscount = new LinkedHashMap<Jobs, Integer>();
			
			List<JobsDTO> jobsdto = new ArrayList<>();
			
			for(Jobs jobss : listOfJobs)
			{
				int applicationCount = jobss.getApplications().size();
			//	listofapplicationscount.put(jobss, applicationCount);
				
				JobsDTO jobs = new JobsDTO();
				jobs.setId(jobss.getId());
				jobs.setTitle(jobss.getTitle());
				jobs.setLocation(jobss.getLocation());
				jobs.setExperienceLevel(jobss.getExperienceLevel());
				jobs.setCompany(jobss.getCompany());
				jobs.setDescription(jobss.getDescription());
				jobs.setRequirements(jobss.getRequirements());
				jobs.setSalary(jobss.getSalary());
				jobs.setEmployementType(jobs.getEmployementType());
				jobs.setPostingDate(jobss.getPostingDate());
				jobs.setApplicationcount(applicationCount);
				
				jobsdto.add(jobs);
				
			}
			if(jobsdto.isEmpty() == false)
			{
				//Collection<Integer> listofapplications = listofapplicationscount.values();
				//List<Integer> listofapplicatons = (List<Integer>)listofapplicationscount.values();
			//	model.addAttribute("listofapplicationscount", listofapplicationscount);
			
				return new ResponseEntity<>(jobsdto,HttpStatus.OK);
			}
			else if(jobsdto.isEmpty() == true)
			{
				return new ResponseEntity<>("jobsempty",HttpStatus.OK);
			}

			//model.addAttribute("listOfJobs", listOfJobs);			
		}
		return new ResponseEntity<>("Employerlogin",HttpStatus.OK);
	}

	
	@PostMapping("/employee/jobs/editjobs")
	public ResponseEntity<?> editPerticularJob(@RequestParam("id") Long Id, HttpSession session) 
	{
       
		EmployerDetails employee = (EmployerDetails)session.getAttribute("empobject");
		
		Optional<Jobs> job = jobservice.findJobById(Id);
        
		if (job.isPresent() == true) 
		{
			Jobs jobs = job.get();

			if (jobs.getEmployerdetails().getId().equals(employee.getId()))
			{
				//model.addAttribute("jobs", jobs);
				
				JobsDTO jobdto = new JobsDTO();
				
				jobdto.setId(jobs.getId());
				jobdto.setTitle(jobs.getTitle());
				jobdto.setLocation(jobs.getLocation());
				jobdto.setExperienceLevel(jobs.getExperienceLevel());
				jobdto.setCompany(jobs.getCompany());
				jobdto.setDescription(jobs.getDescription());
				jobdto.setRequirements(jobs.getRequirements());
				jobdto.setSalary(jobs.getSalary());
				jobdto.setEmployementType(jobs.getEmployementType());
				jobdto.setPostingDate(jobs.getPostingDate());
				//jobs.setApplicationcount(applicationCount);
              
				return new ResponseEntity<>(jobdto,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("jobpost",HttpStatus.OK);
	}

	@PostMapping("/employee/jobs/update")
	public String updateJob(@RequestBody Jobs job) {
		// EmployerDetails emp = service.getById(job.getEmployerdetails().getId());
		
		Optional<Jobs> databaseJob = jobservice.findById(job.getId());

		if (databaseJob.isPresent()) {
			Jobs existingjob = databaseJob.get();

			// existingjob.setEmployerdetails(emp);
			existingjob.setTitle(job.getTitle());

			existingjob.setLocation(job.getLocation());

			existingjob.setCompany(job.getCompany());

			existingjob.setDescription(job.getDescription());

			existingjob.setRequirements(job.getRequirements());

			existingjob.setSalary(job.getSalary());

			existingjob.setEmployementType(job.getEmployementType());

			existingjob.setExperienceLevel(job.getExperienceLevel());

			existingjob.setPostingDate(job.getPostingDate());

			jobservice.jobPost(existingjob);

			return "myJobs";
		} else {
			return "home";
		}

	}

	// this method is for deleteing the existing jobs by their job.
	@GetMapping("/employee/jobs/deletejob")
	public ResponseEntity<?> deleteJob(@RequestParam("id") Long Id) 
	{
		
		// get the job using the job id
		Optional<Jobs> jobss = jobservice.findById(Id);
      
		if (jobss.isPresent() == true) 
		{
			Jobs job = jobss.get();
            
			//get the emp object by the empid which is been extracted from job object as both are mapped.
			EmployerDetails emp = service.getById(job.getEmployerdetails().getId());

			if (emp != null) 
			{
				//removing the listofjobs from employee job list 
				emp.getListOfJobs().remove(job);
				
			    //applicationservice.deleteByJobId(job.getId());
				jobservice.deleteById(job.getId());
				
				
				// before navigating to the myjob giving the remaining list of jobs for the employer
				List<Jobs> listOfJobs = jobservice.listOfJobsByEmpId(emp.getId());
				
				List<JobsDTO> jobsdto = new ArrayList<>();
				
				for(Jobs jobs : listOfJobs)
				{
					int applicationCount = jobs.getApplications().size();
				//	listofapplicationscount.put(jobss, applicationCount);
					
					JobsDTO jobsss = new JobsDTO();
					
					jobsss.setId(jobs.getId());
					jobsss.setTitle(jobs.getTitle());
					jobsss.setLocation(jobs.getLocation());
					jobsss.setExperienceLevel(jobs.getExperienceLevel());
					jobsss.setCompany(jobs.getCompany());
					jobsss.setDescription(jobs.getDescription());
					jobsss.setRequirements(jobs.getRequirements());
					jobsss.setSalary(jobs.getSalary());
					jobsss.setEmployementType(jobs.getEmployementType());
					jobsss.setPostingDate(jobs.getPostingDate());
					
					jobsss.setApplicationcount(applicationCount);
					
					jobsdto.add(jobsss);
					
				}
				if(jobsdto.isEmpty() == false)
				{
					//System.out.println("if block in delete will be executed when list of job is avail");
					//Collection<Integer> listofapplications = listofapplicationscount.values();
					//List<Integer> listofapplicatons = (List<Integer>)listofapplicationscount.values();
				//	model.addAttribute("listofapplicationscount", listofapplicationscount);
					
					
					return new ResponseEntity<>(jobsdto,HttpStatus.OK);
				}
				else
				{
					//System.out.println("else block in delete will be executed when list of job is empty");
					return new ResponseEntity<>("emptyjoblist",HttpStatus.OK);	
				}
				
				
				//model.addAttribute("listOfJobs", listOfJobs);
			}
			else
			{
				//System.out.println("to be executed if employee id is not present or not logged in");
			return new ResponseEntity<>("employeesignup",HttpStatus.OK);
			}
		} else {
			//System.out.println("else block in delete will be executed when job is not present while checking by job id in the database");
			return new ResponseEntity<>("jobpost",HttpStatus.OK);
		}

	}
	
	@PostMapping("/employee/applications")
	public ResponseEntity<?> showApplicationsDetails(@RequestParam("id")Long Id, HttpSession session)
	{
		//EmployerDetails emp =(EmployerDetails)session.getAttribute("empobject");
		//System.out.println(emp);
		
		List<Applications> listofapplicationsbyjobId = applicationservice.getApplicationsByJobId(Id);
		
		if(listofapplicationsbyjobId.isEmpty() == false)
		{
		
			List<ApplicationsDTO> appDTOData = new ArrayList<>();
			for(Applications app : listofapplicationsbyjobId)
			{
				
				ApplicationsDTO appdto = new ApplicationsDTO();
				appdto.setId(app.getId());
				appdto.setEmail(app.getEmail());
				appdto.setName(app.getName());
				appdto.setStatus(app.getStatus());
				appdto.setApplicationDate(app.getApplicationDate());
				appdto.setJobTitle(app.getJobs().getTitle());
				
				
				appDTOData.add(appdto);
			}
			//System.out.println("if block executes when applications are available");
			//model.addAttribute("listofapplicationsbyjobId", listofapplicationsbyjobId);
			return new ResponseEntity<>(appDTOData,HttpStatus.OK);
		}
		else
		{
			Optional<Jobs> job = jobservice.findById(Id);
			if(job.isEmpty() == false)
			{
				Jobs jobs = job.get();
				String title = jobs.getTitle();
				return new ResponseEntity<>(title,HttpStatus.OK);
			}
			
			return new ResponseEntity<>("applicationsnotavailable",HttpStatus.OK);
			
		}
		
		
		
	}
	
	@GetMapping("/application/view/resume")
	public ResponseEntity<?> viewResume(@RequestParam("id") Long Id) {    

	    Applications app = applicationservice.getApplicationsByApplicationsId(Id);
	    
	    if (app != null) {
	        byte[] resumedata = app.getResume();
	        
	        // Create HttpHeaders object
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF); // Set the content type
	        
	        // Optional: Set Content-Disposition header
	       // headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"resume.pdf\""); // Change filename as needed
	        
	        return new ResponseEntity<>(resumedata, headers, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("resumenotfound", HttpStatus.OK);
	    }
	}


@GetMapping("/logout")

public String logout(HttpSession session)
{
	
	session.removeAttribute("empobject");

	return "loggedout";
}
}
