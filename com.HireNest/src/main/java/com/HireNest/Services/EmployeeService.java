package com.HireNest.Services;

import java.util.List;
import java.util.Map;

import com.HireNest.Entities.EmployerDetails;
import com.HireNest.Entities.Jobs;

public interface EmployeeService {
	
	boolean addEmployee(EmployerDetails empdetails);
	
	EmployerDetails checkEmailAndPassword(String email, String password);
	
	EmployerDetails getById(Long empId);


}
