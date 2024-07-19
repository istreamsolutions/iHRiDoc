package com.istream.idoc.orm;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.istream.ihr.orm.Employee;


/**
 * The persistent class for the document database table.
 * @author rishithaa
 */
@NamedQueries( {
	@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
	})

@Entity
@Table(name="document", catalog = "ihr")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date creationTs;

	private Integer documentId;	
	private String documentName;
	private String documentType;
	private String documentUuid;
	private Date lastUpdatedTs;
	

	//bi-directional many-to-one association to Folder
	@ManyToOne
	@JoinColumn(name="Folder_id")
	private Folder folder;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Created_by")
	private Employee empCreatedBy;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Updated_by")
	private Employee empUpdatedBy;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	public Document() {
	}

	
	public Document(Integer documentId, String documentUuid, String documentName, String documentType, Folder folder, Date creationTs, Date lastUpdatedTs,
	         Employee empCreatedBy, Employee empUpdatedBy, Employee employee) {
	this.documentId = documentId;
	this.documentUuid = documentUuid;
	this.documentName = documentName;
	this.documentType = documentType;
	this.folder = folder;
	this.creationTs = creationTs;
	this.lastUpdatedTs = lastUpdatedTs;
	this.empCreatedBy = empCreatedBy;
	this.empUpdatedBy = empUpdatedBy;
	this.employee = employee;
}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "Document_id", unique = true, nullable = false)
	public Integer getDocumentId() {
		return documentId;
	}

	
	@Column(name = "Creation_ts", nullable = false, length = 0)
	public Date getCreationTs() {
		return creationTs;
	}

	public void setCreationTs(Date creationTs) {
		this.creationTs = creationTs;
	}


		
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	
	@Column(name = "Document_name", nullable = false, length = 100)
	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Column(name = "Document_type",  length = 20)
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@Column(name = "Document_uuid", nullable = false, length = 50)
	public String getDocumentUuid() {
		return documentUuid;
	}
	public void setDocumentUuid(String documentUuid) {
		this.documentUuid = documentUuid;
	}
	
	@Column(name = "Last_update_ts", nullable = false, length = 0)
	public Date getLastUpdatedTs() {
		return lastUpdatedTs;
	}

	public void setLastUpdatedTs(Date lastUpdatedTs) {
		this.lastUpdatedTs = lastUpdatedTs;
	}

	@ManyToOne
	@JoinColumn(name="Folder_id")
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	

	@ManyToOne
	@JoinColumn(name="Created_by")
	public Employee getEmpCreatedBy() {
		return empCreatedBy;
	}

	public void setEmpCreatedBy(Employee empCreatedBy) {
		this.empCreatedBy = empCreatedBy;
	}

	@ManyToOne
	@JoinColumn(name="Updated_by")
	public Employee getEmpUpdatedBy() {
		return empUpdatedBy;
	}

	public void setEmpUpdatedBy(Employee empUpdatedBy) {
		this.empUpdatedBy = empUpdatedBy;
	}

	@ManyToOne
	@JoinColumn(name="employee_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}