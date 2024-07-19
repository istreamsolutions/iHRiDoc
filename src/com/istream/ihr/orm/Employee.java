package com.istream.ihr.orm;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "employee", catalog = "ihr")
public class Employee implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String homePhone;
	private String mobilePhone;
	private String workPhone;
	private String ssn;
	private String gender;
	private Date birthDate;
	private Date startDate;
	private Date endDate;
	private BigDecimal baseSalaryAmt;
	private BigDecimal bonusAmt;
	private String designation;
	private Date creationTs;
	private Date lastUpdateTs;
	
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<EmpClientAs> empClientAses = new HashSet<EmpClientAs>(0);
	private Set<EmpHealthPlan> empHealthPlans = new HashSet<EmpHealthPlan>(0);
	private Set<EmpImmigrationDtl> empImmigrationDtls = new HashSet<EmpImmigrationDtl>(0);	
	
	/*
	private UserAccount userAccount = new UserAccount();
	private Set<TimeSheet> timeSheets = new HashSet<TimeSheet>(0);
	private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);
	*/
	
	public Employee() {
	}

	public Employee(Integer employeeId, String firstName, String lastName, String middleName, String email, String homePhone,
			String mobilePhone, String workPhone, String ssn,String gender, Date birthDate, Date startDate, Date endDate,
			BigDecimal baseSalaryAmt, BigDecimal bonusAmt, String designation, Date creationTs, Date lastUpdateTs,
			Set<Address> addresses, Set<EmpClientAs> empClientAses, Set<EmpHealthPlan> empHealthPlans, Set<EmpImmigrationDtl> empImmigrationDtls
			/*, Set<TimeSheet> timeSheets, Set<UserAccount> userAccounts*/) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.email = email;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.workPhone = workPhone;
		this.ssn = ssn;
		this.gender = gender;
		this.birthDate = birthDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.baseSalaryAmt = baseSalaryAmt;
		this.bonusAmt = bonusAmt;
		this.designation = designation;
		this.creationTs = creationTs;
		this.lastUpdateTs = lastUpdateTs;
		
		this.addresses = addresses;
		this.empClientAses = empClientAses;
		this.empHealthPlans = empHealthPlans;
		this.empImmigrationDtls = empImmigrationDtls;
		/*
		this.timeSheets = timeSheets;
		this.userAccounts = userAccounts;
		*/
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "employee_id", unique = true, nullable = false)
	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "First_name", nullable = false, length = 60)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "Last_name", nullable = false, length = 60)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Middle_name", length = 60)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "Email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Home_phone", length = 16777215)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "Mobile_phone", nullable = false, length = 16777215)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "Work_phone", length = 16777215)
	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	@Column(name = "SSN")
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Birth_date", length = 0)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Start_date", nullable = false, length = 0)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "End_date", length = 0)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "Base_salary_amt", nullable = false, precision = 9)
	public BigDecimal getBaseSalaryAmt() {
		return this.baseSalaryAmt;
	}

	public void setBaseSalaryAmt(BigDecimal baseSalaryAmt) {
		this.baseSalaryAmt = baseSalaryAmt;
	}

	@Column(name = "Bonus_amt", precision = 9)
	public BigDecimal getBonusAmt() {
		return this.bonusAmt;
	}

	public void setBonusAmt(BigDecimal bonusAmt) {
		this.bonusAmt = bonusAmt;
	}

	@Column(name = "Designation", nullable = false, length = 20)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creation_ts", nullable = false, length = 0)
	public Date getCreationTs() {
		return this.creationTs;
	}

	public void setCreationTs(Date creationTs) {
		this.creationTs = creationTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Last_update_ts", nullable = false, length = 0)
	public Date getLastUpdateTs() {
		return this.lastUpdateTs;
	}

	public void setLastUpdateTs(Date lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}

	@OneToMany(mappedBy = "employeeID")
	public Set<EmpImmigrationDtl> getEmpImmigrationDtls() {
		return this.empImmigrationDtls;
	}

	public void setEmpImmigrationDtls(Set<EmpImmigrationDtl> empImmigrationDtls) {
		this.empImmigrationDtls = empImmigrationDtls;
	}

	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<TimeSheet> getTimeSheets() {
		return this.timeSheets;
	}

	public void setTimeSheets(Set<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}
	*/

	@OneToMany(mappedBy = "employeeID")
	public Set<EmpHealthPlan> getEmpHealthPlans() {
		return this.empHealthPlans;
	}

	public void setEmpHealthPlans(Set<EmpHealthPlan> empHealthPlans) {
		this.empHealthPlans = empHealthPlans;
	}

	@OneToMany(mappedBy = "employeeID")
	public Set<EmpClientAs> getEmpClientAses() {
		return this.empClientAses;
	}

	public void setEmpClientAses(Set<EmpClientAs> empClientAses) {
		this.empClientAses = empClientAses;
	}

	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeID")
	public Set<UserAccount> getUserAccounts() {
		return this.userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
	*/

	@OneToMany(mappedBy = "employeeID")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	/*
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	*/
}
