package com.istream.ihr.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.istream.ihr.service.EmployeeService;
import com.istream.ihr.vo.AddEmployeeReq;
import com.istream.ihr.vo.AddEmployeeRes;
import com.istream.ihr.vo.EditEmployeeReq;
import com.istream.ihr.vo.EditEmployeeRes;
import com.istream.ihr.vo.GetEmployeeDetailsRes;
import com.istream.ihr.vo.SearchEmployeeReq;
import com.istream.ihr.vo.SearchEmployeeRes;

@RestController
public class EmployeeController {
	
	@Autowired
    EmployeeService employeeService;  //Service which will do all data retrieval/manipulation work
 
	//------------------- Create Employee --------------------------------------------------------
	
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<AddEmployeeRes> createEmployee(@RequestBody AddEmployeeReq addEmployeeReq) {
        
        AddEmployeeRes response = employeeService.createEmployee(addEmployeeReq);
 
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<AddEmployeeRes>(response, HttpStatus.CREATED);
    }
    
    //------------------- Get All Employees --------------------------------------------------------
    
    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<SearchEmployeeRes>> getAllEmployees() {
    	
        List<SearchEmployeeRes> employees = employeeService.getAllEmployees();
        
        if(employees.isEmpty()){
            return new ResponseEntity<List<SearchEmployeeRes>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SearchEmployeeRes>>(employees, HttpStatus.OK);
    }
	
    //------------------- Search Employees --------------------------------------------------------
    
    @RequestMapping(value = "/employee/search/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<List<SearchEmployeeRes>> searchEmployees(@RequestBody SearchEmployeeReq searchEmployeeReq) {
    	
        List<SearchEmployeeRes> employees = employeeService.searchEmployees(searchEmployeeReq);
        
        if(employees.isEmpty()){
            return new ResponseEntity<List<SearchEmployeeRes>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SearchEmployeeRes>>(employees, HttpStatus.OK);
    }
    
    //------------------- Get Employee Details by Id --------------------------------------------------------
    
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetEmployeeDetailsRes> getEmployeeDetails(@PathVariable("id") Long id) {
    	
        System.out.println("Fetching User with id " + id);
        GetEmployeeDetailsRes employee = employeeService.getEmployeeDetails(id);
        if (employee == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<GetEmployeeDetailsRes>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GetEmployeeDetailsRes>(employee, HttpStatus.OK);
    }
    
    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/employee/edit/", method = RequestMethod.PUT)
    public ResponseEntity<EditEmployeeRes> updateEmployee(@RequestBody EditEmployeeReq editEmployeeReq) {
         
        EditEmployeeRes response = employeeService.updateEmployee(editEmployeeReq);
        return new ResponseEntity<EditEmployeeRes>(response, HttpStatus.OK);
    }
}
