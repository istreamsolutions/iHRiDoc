package com.istream.ihr.dao;

import java.util.List;

import com.istream.ihr.orm.Employee;
import com.istream.ihr.vo.SearchEmployeeReq;

public interface EmployeeDao {
	
	public void createEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> searchEmployees(SearchEmployeeReq searchEmployeeReq);
	
	public Employee getEmployeeDetails(Long id);
	
	public void updateEmployee(Employee employee);
	
}
