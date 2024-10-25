package com.HireNest.Services;

import java.util.Comparator;

import com.HireNest.Entities.Jobs;

public class JobsSortingHelper implements Comparator<Jobs> {


	@Override
	public int compare(Jobs o1, Jobs o2) 
	{
	   
	    int titleResult = o1.getTitle().compareTo(o2.getTitle());
	    return titleResult;
	   	
	   	
	   	
//	   	int experienceLevel = o1.getExperienceLevel().compareTo(o2.getExperienceLevel());
//	   	int location = o1.getLocation().compareTo(o2.getLocation());
//	   	int company = o1.getCompany().compareTo(o2.getCompany());
//		
	}

}
