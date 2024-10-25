package com.HireNest.Services;

import java.util.List;

import com.HireNest.Entities.Jobs;
import com.HireNest.Entities.UserProfile;

public interface UserProfileService 
{
	boolean createProfile(UserProfile userprofile, String email);
	
    boolean checkProfileExistsOrNot(String email);
    
    List<Jobs> allTheJobs();

}
