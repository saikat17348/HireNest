package com.HireNest.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.ApplicationsDTO;
import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.JobsDTO;
import com.HireNest.Entities.UserProfile;
import com.HireNest.Services.UserProfileService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserProfileController {
	
	@Autowired
	UserProfileService service;
	
	@PostMapping("/createprofile")
	public ResponseEntity<?> createProfile(@RequestParam String fullName, @RequestParam String email, @RequestParam String phone, @RequestParam(required = false) String location,
			@RequestParam(required = false) int experience, @RequestParam(required = false) String skills , @RequestParam(required = false) String bio, @RequestParam(required = false) MultipartFile resume, 
			@RequestParam(required = false) MultipartFile photo, HttpSession session)
	{
		
		boolean status = service.checkProfileExistsOrNot(email);
	
		if(status == false)
		{
		UserProfile profile = new UserProfile();
		profile.setFullName(fullName);
		profile.setEmail(email);
		profile.setPhone(phone);
		profile.setLocation(location);
		profile.setExperience(experience);
		profile.setSkills(skills);
		profile.setBio(bio);
		
		try {
			if (photo != null) {
				profile.setPhoto(photo.getBytes());
			} else {
				profile.setPhoto(null);
				
			}

		} catch (IOException e) {
			System.out.print("exception photo");
		}
		
		try {
			if(resume != null)
			{
				profile.setResume(resume.getBytes());
				
			}
			else
			{
				profile.setResume(null);
			}
		}
		catch(IOException e)
		{
			System.out.print("exception resume");
		}
		   boolean statusss = service.createProfile(profile, email);
		   
		   if(statusss == true)
		   {
			
			   // get the list of all the jobs
				List<Jobs> listOfJobs = service.allTheJobs();
			
				if(listOfJobs.isEmpty() == false)
				{
					
					List<JobsDTO> newlistofjob = new ArrayList<>();
					
					// extracting the details from the allthejobs and setting it to the dtos(jobsdto, applicationsdto); 
					for(Jobs alljobs : listOfJobs)
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
					}
					return new ResponseEntity<>(newlistofjob,HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<>("emptylist",HttpStatus.OK);
				}
		   }
		   else
		   {
			   return new ResponseEntity<>("notcreated",HttpStatus.OK);
		   }
		
		}
		else
		{
			 return new ResponseEntity<>("emailexists",HttpStatus.OK);
		}
		
	}

}
