package com.HireNest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.User;
import com.HireNest.Entities.UserProfile;
import com.HireNest.Repositories.JobsRepository;
import com.HireNest.Repositories.UserProfileRepository;
import com.HireNest.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserProfileImplementation implements UserProfileService 
{
	@Autowired
	UserProfileRepository repo;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	JobsRepository jobsrepo;
	
	
    // matching the user email with profile creation email to verify user is creating jobprofile with same emailid
	@Override
	public boolean createProfile(UserProfile userprofile, String email) 
	{	
		User user = userrepo.findByEmail(email);
	
		if(user != null && user.getEmail().equals(email))
		{
			repo.save(userprofile);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	// checking when the user is logging in the hirest.com either he has profile with us or not.
	public boolean checkProfileExistsOrNot(String email)
	{
		
		

		boolean existsOrNot = repo.existsByEmail(email);
        
		if(existsOrNot == true)
		{
			return true;
		}
		return false;
		
		
	}

	@Override
	public List<Jobs> allTheJobs() 
	{
		 List<Jobs> allTheJobs = jobsrepo.findAll();
	
		if(allTheJobs != null)
		{
			return allTheJobs;
		}
		else
		{
			return null;
		}
	}

}
