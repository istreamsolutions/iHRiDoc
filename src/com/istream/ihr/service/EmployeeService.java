package com.istream.ihr.service;

import java.util.List;

import com.istream.ihr.vo.AddEmployeeReq;
import com.istream.ihr.vo.AddEmployeeRes;
import com.istream.ihr.vo.EditEmployeeReq;
import com.istream.ihr.vo.EditEmployeeRes;
import com.istream.ihr.vo.GetEmployeeDetailsRes;
import com.istream.ihr.vo.SearchEmployeeReq;
import com.istream.ihr.vo.SearchEmployeeRes;

public interface EmployeeService {
	
	AddEmployeeRes createEmployee(AddEmployeeReq addEmployeeReq);
	
	List<SearchEmployeeRes> getAllEmployees();
	
	List<SearchEmployeeRes> searchEmployees(SearchEmployeeReq searchEmployeeReq);
	
	GetEmployeeDetailsRes getEmployeeDetails(Long id);
	
	EditEmployeeRes updateEmployee(EditEmployeeReq editEmployeeReq);
	
	/*
	Employee findById(long id);
	
	Employee findByName(String name);
	
	
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeById(long id);

	List<Employee> findAllEmployees(); 
	
	
	*/
}
