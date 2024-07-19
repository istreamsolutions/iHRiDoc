package com.istream.ihr.dao;

import java.util.List;

import com.istream.ihr.orm.TimeSheet;

public interface TimesheetDAO {

	public void saveTimeSheet(TimeSheet timeSheet);
	
	public List<TimeSheet> getTimeSheets(Long employeeId, Integer month,  Integer year);		
	
}
