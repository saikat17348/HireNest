package com.HireNest.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.ApplicationsDTO;
import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.JobsDTO;
import com.HireNest.Entities.User;
import com.HireNest.Services.UserProfileService;
import com.HireNest.Services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
    @Autowired
    UserProfileService userprofileservice;
	
    
    // done with js and configured
	@PostMapping("/register")
	public String signUp(@RequestBody User user)
	{

		boolean statusCheckEmail = service.addUser(user);
		if(statusCheckEmail == true)
		{
			return "hirenestUserLogin";
		}
		else
		{
		     return "home";
		}
	}
		
	
	// done with js and configured.
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User users, Model model, HttpSession session)
	{
		User user = service.checkEmailAndPassword(users.getEmail(), users.getPassword());
		
		if(user != null)
		{
			// getting the userid and saving to the session because i need the current user id in the apply jobs page.
			Long userid = user.getId();
			session.setAttribute("userId",userid);
			
			
			// checking the user has profile or not if has profile will land directly to job search and view all the jobs page.
			boolean userprofile = userprofileservice.checkProfileExistsOrNot(users.getEmail());
			if(userprofile == true)
			{
				// while landing to the search page display all the jobs
				List<Jobs> allTheJobs = userprofileservice.allTheJobs();
			     
				if(allTheJobs.isEmpty() == false)
				{
					
					//model.addAttribute("jobs", allTheJobs);
					List<JobsDTO> newlistofjob = new ArrayList<>();
					// extracting the details from the allthejobs and setting it to the dtos(jobsdto, applicationsdto); 
				    
					for(Jobs alljobs : allTheJobs)
					{
						JobsDTO userdto = new JobsDTO();
						userdto.setId(alljobs.getId());
						userdto.setTitle(alljobs.getTitle());
						userdto.setLocation(alljobs.getLocation());
						userdto.setExperienceLevel(alljobs.getExperienceLevel());
						userdto.setCompany(alljobs.getCompany());
						userdto.setDescription(alljobs.getDescription());
						userdto.setRequirements(alljobs.getRequirements());
						userdto.setSalary(alljobs.getSalary());
						userdto.setEmployementType(alljobs.getSalary());
						userdto.setPostingDate(alljobs.getPostingDate());
						
						List<ApplicationsDTO> applicationsdto = new ArrayList<>();
						List<Applications> listofapp = alljobs.getApplications();
						
						for(Applications app : listofapp)
						{
							ApplicationsDTO appdto = new ApplicationsDTO();
							appdto.setId(app.getId());
							appdto.setEmail(app.getEmail());
							appdto.setApplicationDate(app.getApplicationDate());
							appdto.setName(app.getName());
							appdto.setStatus(app.getStatus());
							applicationsdto.add(appdto);
						}
						userdto.setApplicationsDTO(applicationsdto);
						
						
						newlistofjob.add(userdto);
						
						//converting list of applications to applicationsdto
						
						//List<ApplicationsDTO> listofapplications = alljobs.getApplications().st
					}
					
					return new ResponseEntity<>(newlistofjob,HttpStatus.OK);
					
				
					//return new ResponseEntity<>(allTheJobs, HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<>("emptylist",HttpStatus.OK);
				}
			}
			else
			{
				return new ResponseEntity<>("profileCreation", HttpStatus.OK);
			}
			
		}
		else
		{
			return new ResponseEntity<>("hirenestUserSignUp", HttpStatus.OK);
		}
		
	}

}
