/**
 * 
 */
package com.istream.ihr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istream.ihr.dao.TimesheetDAO;
import com.istream.ihr.orm.TimeSheet;
import com.istream.ihr.service.TimeSheetService;
import com.istream.ihr.vo.AddTimesheetRes;
import com.istream.ihr.vo.TimesheetSearchReq;

/**
 * @author Istream2
 *
 */
@Service("timesheetService")
public class TimeSheetServiceImpl implements TimeSheetService {
	
	@Autowired
	private TimesheetDAO timesheetDAO;

	/* (non-Javadoc)
	 * @see com.istream.ihr.service.TimeSheetService#getTimeSheets(com.istream.ihr.vo.TimesheetSearch)
	 */
	@Override
	public List<TimeSheet> getTimeSheets(TimesheetSearchReq searchRequest) {
		List<TimeSheet> timeSheets = this.timesheetDAO.getTimeSheets(searchRequest.getEmployeeId(),
				searchRequest.getMonth(), searchRequest.getYear());	
		return timeSheets;
	}

	/* (non-Javadoc)
	 * @see com.istream.ihr.service.TimeSheetService#submitTimeSheet(com.istream.ihr.vo.orm.TimeSheet)
	 */
	@Override
	public AddTimesheetRes submitTimeSheet(TimeSheet timeSheet) {
		this.timesheetDAO.saveTimeSheet(timeSheet);
		return new AddTimesheetRes();
	}

	/* (non-Javadoc)
	 * @see com.istream.ihr.service.TimeSheetService#approveTimeSheet(com.istream.ihr.vo.orm.TimeSheet)
	 */
	@Override
	public void approveTimeSheet(TimeSheet timeSheet) {
		// TODO Auto-generated method stub

	}

}
