package com.HireNest.Services;

import java.util.List;
import java.util.Optional;

import com.HireNest.Entities.Applications;

public interface ApplicationService 
{
	void saveApplication(Applications application);

	int getByEmpId(Long id);
	
	List<Applications> getApplicationsByEmpId(Long Id);

	List<Applications> getApplicationsByJobId(Long id);

	Applications getApplicationsByApplicationsId(Long id);

	void deleteByJobId(Long id);

	Optional<Applications> getListOfApp(Long id);

}
