package com.istream.ihr.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.istream.ihr.orm.Employee;
import com.istream.ihr.orm.Project;
import com.istream.ihr.orm.TimeSheet;
import com.istream.ihr.service.TimeSheetService;
import com.istream.ihr.vo.AddTimesheetReq;
import com.istream.ihr.vo.AddTimesheetRes;
import com.istream.ihr.vo.SearchEmployeeReq;
import com.istream.ihr.vo.SearchEmployeeRes;
import com.istream.ihr.vo.TimesheetSearchReq;

@RestController
public class TimeSheetController {
	
	@Autowired
	TimeSheetService timesheetService;
	
	@RequestMapping(value = "/timesheet/", method = RequestMethod.POST)
    public ResponseEntity<AddTimesheetRes> addTimesheet(@RequestBody AddTimesheetReq addTimesheetReq) {
		System.out.println(addTimesheetReq);
		TimeSheet timeSheet = convertReqToDao(addTimesheetReq);
		AddTimesheetRes response = timesheetService.submitTimeSheet(timeSheet);		
        return new ResponseEntity<AddTimesheetRes>(response, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/timesheet/search/", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<List<TimeSheet>> searchEmployees(@RequestBody TimesheetSearchReq timesheetSearchReq) {    	
		List<TimeSheet> timesheets = timesheetService.getTimeSheets(timesheetSearchReq);
		 return new ResponseEntity<List<TimeSheet>>(timesheets, HttpStatus.OK);
    }

	private TimeSheet convertReqToDao(AddTimesheetReq addTimesheetReq) {
		Employee employee = new Employee();
		 employee.setEmployeeId(addTimesheetReq.getEmployeeId());
		 Project project = new Project();
		 project.setProjectId(addTimesheetReq.getProjectId());
		 Date startDate = addTimesheetReq.getDate();
		 int billedHours = addTimesheetReq.getHours();
		 Integer nonBilledHours = 1;
		 String note = "note";
		 String description = addTimesheetReq.getDescription();
		 String status = "NA";
		 Date creationTs = new Date();
		 Date lastUpdateTs = new Date();
			
		TimeSheet timeSheet = new TimeSheet(employee, project, startDate, billedHours, nonBilledHours, note, status, 
				creationTs, lastUpdateTs, description);
		return timeSheet;
	}

}
