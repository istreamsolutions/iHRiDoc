package com.istream.ihr.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istream.ihr.dao.TimesheetDAO;
import com.istream.ihr.orm.Employee;
import com.istream.ihr.orm.Project;
import com.istream.ihr.orm.TimeSheet;
import com.istream.ihr.util.DAOUtil;

@Repository
public class TimesheetDAOImpl implements TimesheetDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveTimeSheet(TimeSheet timeSheet) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(timeSheet);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<TimeSheet> getTimeSheets(Long employeeId, Integer month,  Integer year) {
		Session session = getSessionFactory().openSession();
		String hql = "SELECT time_sheet.Time_sheet_id, " +
			    "time_sheet.Time_sheet_date, " +
			    "time_sheet.Billed_Hours, " +
			    "time_sheet.Non_Billed_Hours, " +
			    "time_sheet.Note, " +
			    "time_sheet.Employee_id, " +
			    "time_sheet.Status, " +
			    "time_sheet.project_id, " +
			    "time_sheet.Creation_ts, " +
			    "time_sheet.Last_update_ts, " +
			    "time_sheet.Description," +
			    " project.name" +
			" FROM ihr.time_sheet time_sheet, ihr.project project" +
			" WHERE month(time_sheet.Time_sheet_date) = :month " +
			         " AND YEAR(time_sheet.Time_sheet_date) = :year " +
			         " AND time_sheet.Employee_id = :employeeId" +
			         " AND time_sheet.project_id = project.project_id";
		Query q = session.createSQLQuery(hql)
				.setParameter("month", month)
				.setParameter("year", year)
				.setParameter("employeeId", employeeId);		
		List<Object> resultList = q.list();		
		List<TimeSheet> timeSheets = null;	        
	        if (resultList.size() > 0) {
	        	timeSheets = new LinkedList<TimeSheet>();
	            ListIterator<Object> resultListIterator = (ListIterator<Object>) resultList.listIterator();
	            while(resultListIterator.hasNext()) {
	                Object[] row = (Object[]) resultListIterator.next();
	                TimeSheet timeSheet = new TimeSheet();
	                timeSheet.setTimeSheetId(DAOUtil.getInteger(row[0]));
	                timeSheet.setTimeSheetDate(DAOUtil.getDateFromSqlDate(row[1]));
	                timeSheet.setBilledHours(DAOUtil.getInteger(row[2]));
	                timeSheet.setNonBilledHours(DAOUtil.getInteger(row[3]));
	                timeSheet.setNote(DAOUtil.getString(row[4]));
	                Employee e = new Employee();
	                e.setEmployeeId(DAOUtil.getInteger(row[5]));
	                timeSheet.setEmployee(e);
	                timeSheet.setStatus(DAOUtil.getString(row[6]));
	                Project project = new Project();
	                project.setProjectId(DAOUtil.getInteger(row[7]));
	                project.setName(DAOUtil.getString(row[11]));
	                timeSheet.setProject(project);
	                //timeSheet.setTimeSheetDate(DAOUtil.getDate(row[8]));
	                //timeSheet.setTimeSheetDate(DAOUtil.getDate(row[9]));
	                timeSheet.setDescription(DAOUtil.getString(row[10]));
	                timeSheets.add(timeSheet);
	                
	            }
	        }
	        return timeSheets;		
	}
}
