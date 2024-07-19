package com.istream.ihr.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.istream.ihr.dao.EmployeeDao;
import com.istream.ihr.orm.Employee;
import com.istream.ihr.orm.Role;
import com.istream.ihr.orm.UserAccount;
import com.istream.ihr.vo.SearchEmployeeReq;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private SessionFactory sessionFactory;
	
	@Override
	public void createEmployee(Employee employee) {
		Session session = getSessionFactory().openSession();
		session.saveOrUpdate(employee);
		
		UserAccount usrAccount = new UserAccount();
		usrAccount.setEmployeeID(employee.getEmployeeId());
		Role role = new Role();
		role.setRoleId(2);
		usrAccount.setRole(role);
		usrAccount.setUsername(employee.getEmail());
		usrAccount.setCreationTs(new Date());
		usrAccount.setLastUpdateTs(new Date());
		
		session.saveOrUpdate(usrAccount);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employees = (List<Employee>) criteria.list();
		return employees;
	}
	
	@Override
	public List<Employee> searchEmployees(SearchEmployeeReq searchEmployeeReq) {
		
		Session session = getSessionFactory().openSession();
		List<Employee> employees = null;
		String client = searchEmployeeReq.getClient();
		String name = searchEmployeeReq.getName();

		if (client != null && !"".equals(client.trim())) {

			String hql = "from Employee e where e.employeeId  in "
					+ "(select ec.employee.employeeId from EmpClientAs ec where ec.client.clientId  in"
					+ "(select c.clientId from Client c where c.name like  :searchKey "
					+ "))";

			Query q = session.createQuery(hql);
			if (name != null && !"".equals(name.trim())) {
				hql += " and (e.firstName like :searchName  or e.lastName like :searchName )";

				q.setParameter("searchName", "%" + name + "%");
			}

			q.setParameter("searchKey", "%" + client + "%");

			employees = q.list();

		} else {
			Criteria criteria = session.createCriteria(Employee.class);
			if (name != null && !"".equals(name.trim())) {
				Criterion Cr1 = Restrictions
						.like("firstName", "%" + name + "%");
				Criterion Cr2 = Restrictions.like("lastName", "%" + name + "%");
				criteria.add(Restrictions.or(Cr1, Cr2));
			}
			employees = (List<Employee>) criteria.list();
		}
		return employees;
	}

	@Override
	public Employee getEmployeeDetails(Long id) {
		
		int empID = (int) Long.parseLong(""+id);
		Session session = getSessionFactory().openSession();
		Employee employee = session.get(Employee.class, empID);
		return employee;
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		
		Employee emp = new Employee();
		emp.setEmployeeId(26);
		emp.setFirstName("Test125");
		emp.setLastName("Test125");
		
		try {
			Session session = getSessionFactory().openSession();
			session.saveOrUpdate(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
