package com.HireNest.Services;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.HireNest.Entities.Applications;
import com.HireNest.Entities.EmployerDetails;
import com.HireNest.Entities.Jobs;
import com.HireNest.Repositories.ApplicationsRepository;
import com.HireNest.Repositories.EmployeeRepository;
import com.HireNest.Repositories.JobsRepository;



@Service
public class EmployeeImplementation implements EmployeeService {
	
	@Autowired
	EmployeeRepository emprepo;
	
	@Autowired
	JobsRepository jobrepo;
	
	@Autowired
	ApplicationsRepository apprepo;

	@Override
	public boolean addEmployee(EmployerDetails empdetails) {
		
	    EmployerDetails empobject = emprepo.getByEmail(empdetails.getEmail());
		
	    if(empobject == null)
	    {
	    	emprepo.save(empdetails);
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}

	@Override
	public EmployerDetails checkEmailAndPassword(String email, String password) {

		EmployerDetails empobject = emprepo.findByEmail(email);
   
		if(empobject != null)
		{
			String dbPassword = empobject.getPassword();
			
			if(dbPassword.equals(password))
			{
				return empobject;
			}
		}
		return null;
	}
	
	

	@Override
	public EmployerDetails getById(Long empId) 
	{
		
		 EmployerDetails empObject = emprepo.findEmpById(empId);
		 return empObject;
	}

}
