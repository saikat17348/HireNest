package com.HireNest.Services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.Jobs;
import com.HireNest.Repositories.ApplicationsRepository;

import jakarta.transaction.Transactional;

@Service
public class ApplicationServiceImplementation implements ApplicationService
{
    @Autowired
    ApplicationsRepository repo;
	
	@Override
	public void saveApplication(Applications application) 
	{
		repo.save(application);
	}

	@Override
	public int getByEmpId(Long id) 
	{
		int noofapplications = 0;
		
		List<Applications> list =  repo.findByEmployerDetails_Id(id);
		
		if(list.isEmpty() == false)
		{
			Iterator<Applications> iterator = list.iterator();
			
			while(iterator.hasNext())
			{
				noofapplications = noofapplications+1;
				iterator.next();
			}
		}
		return noofapplications;
	}

	@Override
	public List<Applications> getApplicationsByEmpId(Long Id) 
	{
		List<Applications> list = repo.findByEmployerDetails_Id(Id);
		
		return list;
	}

	@Override
	public List<Applications> getApplicationsByJobId(Long id) 
	{
		List<Applications> list = repo.findByJobs_Id(id);
	
		return list;
	}

	@Override
	public Applications getApplicationsByApplicationsId(Long id) {
		
		Applications app = repo.findApplicationsById(id);
		
		return app;
	}
    
	@Transactional
	@Override
	public void deleteByJobId(Long id) {
		
		 // repo.deleteByJobsId(id);
		
	}

	@Override
	public Optional<Applications> getListOfApp(Long id) {
		
		Optional<Applications> app = repo.findById(id);
		return app;
	}

}
