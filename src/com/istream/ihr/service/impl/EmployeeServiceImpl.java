package com.istream.ihr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.istream.ihr.dao.EmployeeDao;
import com.istream.ihr.orm.Address;
import com.istream.ihr.orm.EmpClientAs;
import com.istream.ihr.orm.EmpHealthPlan;
import com.istream.ihr.orm.EmpImmigrationDtl;
import com.istream.ihr.orm.Employee;
import com.istream.ihr.service.EmployeeService;
import com.istream.ihr.vo.AddEmployeeReq;
import com.istream.ihr.vo.AddEmployeeRes;
import com.istream.ihr.vo.EditEmployeeAddressReq;
import com.istream.ihr.vo.EditEmployeeClientReq;
import com.istream.ihr.vo.EditEmployeeHealthPlanReq;
import com.istream.ihr.vo.EditEmployeeImmigrationDetailReq;
import com.istream.ihr.vo.EditEmployeeReq;
import com.istream.ihr.vo.EditEmployeeRes;
import com.istream.ihr.vo.EmployeeAddressRes;
import com.istream.ihr.vo.EmployeeClientRes;
import com.istream.ihr.vo.EmployeeHealthPlanRes;
import com.istream.ihr.vo.EmployeeImmigrationDetailRes;
import com.istream.ihr.vo.GetEmployeeDetailsRes;
import com.istream.ihr.vo.SearchEmployeeReq;
import com.istream.ihr.vo.SearchEmployeeRes;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public AddEmployeeRes createEmployee(AddEmployeeReq addEmployeeReq) {
		
		//Business Logic here
		//addEmployeeReq.setBirthDate(new Date());//Remove once passing from GUI
		//addEmployeeReq.setStartDate(new Date());//Remove once passing from GUI
		Employee employee = convertReqToDao(addEmployeeReq);
		
		employeeDao.createEmployee(employee);
		
		return new AddEmployeeRes();
	}
	
	private Employee convertReqToDao(AddEmployeeReq addEmployeeReq) {
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(addEmployeeReq, employee);
		employee.setCreationTs(new Date());
		employee.setLastUpdateTs(new Date());
		
		/*
		UserAccount usrAccount = new UserAccount();
		Role role = new Role();
		role.setRoleId(2);
		usrAccount.setRole(role);
		usrAccount.setUsername(addEmployeeReq.getEmail());
		usrAccount.setCreationTs(new Date());
		usrAccount.setLastUpdateTs(new Date());		
		employee.setUserAccount(usrAccount);
		*/
		return employee;
	}
	
	@Override
	public List<SearchEmployeeRes> getAllEmployees() {
		
		List<Employee> employeeList = employeeDao.getAllEmployees();
		return convertDaoToRes(employeeList);
	}
	
	@Override
	public List<SearchEmployeeRes> searchEmployees(SearchEmployeeReq searchEmployeeReq) {
		
		List<Employee> employeeList = employeeDao.searchEmployees(searchEmployeeReq);
		return convertDaoToRes(employeeList);
	}
	
	private List<SearchEmployeeRes> convertDaoToRes(List<Employee> employeeList) {
		
		List<SearchEmployeeRes> responseList = new ArrayList<SearchEmployeeRes>();
		SearchEmployeeRes response;
		
		for(Employee employee: employeeList) {
			response = new SearchEmployeeRes();
			BeanUtils.copyProperties(employee, response);
			responseList.add(response);
		}
		
		return responseList;
	}
	
	@Override
	public GetEmployeeDetailsRes getEmployeeDetails(Long id) {
		
		Employee employee = employeeDao.getEmployeeDetails(id);
		
		return convertDaoToRes(employee);
	}
	
	private GetEmployeeDetailsRes convertDaoToRes (Employee employee) {
		
		GetEmployeeDetailsRes response = new GetEmployeeDetailsRes();
		
		BeanUtils.copyProperties(employee, response);
		
		if(employee.getAddresses() != null && !employee.getAddresses().isEmpty())
			response.setEmpAddress(convertEmpAddressRes(employee.getAddresses().iterator().next()));
		
		if(employee.getEmpClientAses() != null && !employee.getEmpClientAses().isEmpty())
			response.setEmpClient(convertEmpClientRes(employee.getEmpClientAses().iterator().next()));
		
		if(employee.getEmpHealthPlans() != null && !employee.getEmpHealthPlans().isEmpty())
			response.setEmpHealthPlan(convertEmpHealthPlanRes(employee.getEmpHealthPlans().iterator().next()));
		
		if(employee.getEmpImmigrationDtls() != null && !employee.getEmpImmigrationDtls().isEmpty())
			response.setEmpImmigrationDtl(convertEmployeeImmigrationDetailRes(employee.getEmpImmigrationDtls().iterator().next()));
		
		return response;
		
	}
	
	private EmployeeAddressRes convertEmpAddressRes(Address address) {
		
		EmployeeAddressRes response = new EmployeeAddressRes();
		BeanUtils.copyProperties(address, response);
		
		return response;
	}
	
	private EmployeeClientRes convertEmpClientRes(EmpClientAs empClient) {
		
		EmployeeClientRes response = new EmployeeClientRes();
		BeanUtils.copyProperties(empClient, response);
		response.setClientName(empClient.getClient().getName());
		
		return response;		
	}
	
	private EmployeeHealthPlanRes convertEmpHealthPlanRes(EmpHealthPlan empHealthPlan) {
		
		EmployeeHealthPlanRes response = new EmployeeHealthPlanRes();
		BeanUtils.copyProperties(empHealthPlan, response);
		response.setCarrierName(empHealthPlan.getHealthCarrier().getName());
		
		return response;
	}
	
	private EmployeeImmigrationDetailRes convertEmployeeImmigrationDetailRes(EmpImmigrationDtl empImmigrationDtls) {
		
		EmployeeImmigrationDetailRes response = new EmployeeImmigrationDetailRes();
		BeanUtils.copyProperties(empImmigrationDtls, response);
		
		return response;
		
	}
	
	@Override
	public EditEmployeeRes updateEmployee(EditEmployeeReq editEmployeeReq) {
		
		Employee employee = new Employee();
		
		employee = convertEmployeeReqToDao(editEmployeeReq);
		
		//Address
		if(editEmployeeReq.getEmpAddress() != null) {			
			Set<Address> addresses = new HashSet<Address>();
			addresses.add(convertEmployeeAddressReqToDao(editEmployeeReq.getEmpAddress()));
			employee.setAddresses(addresses);
		}
		
		//Client
		if(editEmployeeReq.getEmpClient() != null) {
			Set<EmpClientAs> clientAses = new HashSet<EmpClientAs>();
			clientAses.add(convertEmployeeClientReqToDao(editEmployeeReq.getEmpClient()));
			employee.setEmpClientAses(clientAses);
		}
		
		//Health Plan
		if(editEmployeeReq.getEmpHealthPlan() != null) {
			Set<EmpHealthPlan> healthPlans = new HashSet<EmpHealthPlan>();
			healthPlans.add(convertEmployeeHeathPlanReqToDao(editEmployeeReq.getEmpHealthPlan()));
			employee.setEmpHealthPlans(healthPlans);
		}
		
		//Immigration Details
		if(editEmployeeReq.getEmpImmigrationDtl() != null) {
			Set<EmpImmigrationDtl> immigrationDtls = new HashSet<EmpImmigrationDtl>();
			immigrationDtls.add(convertEmployeeImmDtlReqToDao(editEmployeeReq.getEmpImmigrationDtl()));
			employee.setEmpImmigrationDtls(immigrationDtls);
		}
		
		employeeDao.updateEmployee(employee);
		
		return new EditEmployeeRes();
	}
	
	private Employee convertEmployeeReqToDao(EditEmployeeReq editEmployeeReq) {
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(editEmployeeReq, employee);		
		return employee;
	}
	
	private Address convertEmployeeAddressReqToDao(EditEmployeeAddressReq addressReq) {
		
		Address address = new Address();
		BeanUtils.copyProperties(addressReq, address);
		return address;
	}
	
	private EmpClientAs convertEmployeeClientReqToDao(EditEmployeeClientReq clientReq) {
		
		EmpClientAs client = new EmpClientAs();
		BeanUtils.copyProperties(clientReq, client);
		return client;
	}
	
	private EmpHealthPlan convertEmployeeHeathPlanReqToDao(EditEmployeeHealthPlanReq healthPlanReq) {
		
		EmpHealthPlan healthPlan = new EmpHealthPlan();
		BeanUtils.copyProperties(healthPlanReq, healthPlan);
		return healthPlan;
	}
	
	private EmpImmigrationDtl convertEmployeeImmDtlReqToDao(EditEmployeeImmigrationDetailReq immigrationDtlReq) {
		
		EmpImmigrationDtl immigrationDtl = new EmpImmigrationDtl();
		BeanUtils.copyProperties(immigrationDtlReq, immigrationDtl);
		return immigrationDtl;
	}
	
	/*
	public List<Employee> findAllEmployees() {
		return this.employeeDao.selectAll();
	}
	
	public Employee findById(long id) {
		return employeeDao.findById(id);
	}
	
	public Employee findByName(String name) {
		return null;
	}

	public void updateEmployee(Employee employee) {
	}

	public void deleteEmployeeById(long id) {
	}

	public boolean isEmployeeExist(Employee employee) {
		return true;
	}
	
	@Override
	public List<Employee> searchEmployees(String name, String client) {
		return employeeDao.searchEmployee(name, client);
	}
	*/
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
}
