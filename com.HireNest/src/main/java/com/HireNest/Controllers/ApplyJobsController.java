package com.HireNest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.EmployerDetails;
import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.JobsDTO;
import com.HireNest.Entities.User;

import com.HireNest.Services.ApplicationService;
import com.HireNest.Services.EmployeeService;
import com.HireNest.Services.JobService;
import com.HireNest.Services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ApplyJobsController {
	@Autowired
	JobService jobservice;

	@Autowired
	UserService userservice;

	@Autowired
	ApplicationService applicationservice;

	@Autowired
	EmployeeService employerservice;

	// this api is to fetch the job and extract the title from it and send the title
	// to the js
	@PostMapping("/fetchjobtitle")
	public String fetchJob(@RequestParam Long id) {

		Jobs job = jobservice.searchJobById(id);
		if (job != null) {
//			JobsDTO jobdto = new JobsDTO();
//			jobdto.setTitle(job.getTitle());

			String title = job.getTitle();
			return title;
		} else {
			return null;
		}
	}

	// this api simply check either the job is present or not in the database and
	// send the page name to the js to redirect to the html page.
	@GetMapping("/applyforjob")
	public String openApplyJobsPage(@RequestParam Long id)// Model model)
	{
		Jobs job = jobservice.searchJobById(id);
		// model was used in the template engine but shifted to js so no need of model
		// interface.
		// model.addAttribute("jobs", job);
		if (job != null) {
			return "applyJobs";
		}

		return null;

	}

	// when applyjob form is submitted this api is hitting
	@PostMapping("/jobs/apply")
	public String applyJobs(@RequestParam("id") Long jobId, @RequestParam String name, @RequestParam String email,
			@RequestParam(required = false) MultipartFile resume, HttpSession session)

	{
		// getting the job for which user is applying or submitting the application.
		Jobs job = jobservice.searchJobById(jobId);

		// extracting the employeeDetails from the jobs entity.
		EmployerDetails empdetailsByJobId = job.getEmployerdetails();

		// getting the userId from session which i injected at the time of login in the
		// HttpSession and extracting it from session
		Long userId = (Long) session.getAttribute("userId");

		// using the extracted userId fetching the userObject from the database.
		User dataBaseUser = userservice.getUserById(userId);

		// getting the employerDetails
		EmployerDetails emp = employerservice.getById(job.getEmployerdetails().getId());

		if (dataBaseUser != null) {

			// checking the logged in user is equal to the user who is applying, if yes he
			// is allowed to apply or submit the application
			if (dataBaseUser.getEmail().equals(email)) {
				
				// creating the object because i have to set the user and job as applications is
				// mapped with job and user entity
				Applications application = new Applications();

				// setting the job to the application entity bsc application is mapped
				application.setJobs(job);

				// setting the user to the application bsc this is also mapped.
				application.setUser(dataBaseUser);

				// setting the employeeDetails to the applications

				application.setEmployerDetails(empdetailsByJobId);

				application.setName(name);
				application.setEmail(email);
				try {
					if (resume == null) {
						application.setResume(null);
					} else {
						application.setResume(resume.getBytes());
					}
				} catch (Exception e) {

					System.out.println(e);
					System.out.println("error in the apply jobs controller while setting the resume");
				}

				// setting the applications to the job
				job.setApplicationsToJobs(application);

				// setting the applications to the employee
				emp.setApplicatonsToTheEmployee(application);

				applicationservice.saveApplication(application);
				String msg = "Successfully Applied";
				// model.addAttribute("successMessage",msg);
				return "displaySearchJobs";
			} else {
				
				return "invalid email";
			}

		} else {
			 String errormsg = "Please log in";
			// model.addAttribute("errorMessage", errormsg);
			
			return errormsg;
		}
	}
}
