package com.istream.idoc.orm;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.istream.ihr.orm.Employee;



/**
 * The persistent class for the folder database table.
 * 
 */

@Entity
@Table(name="folder")
@NamedQuery(name="Folder.findAll", query="SELECT f FROM Folder f")
public class Folder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Folder_id")
	@GeneratedValue(strategy = IDENTITY)
	private int folder_id;

	@Column(name="Creation_ts")
	private Date creation_ts;

	@Column(name="Folder_name")
	private String folder_name;

	@Column(name="immi_type_id")
	private int immi_type_id;

	@Column(name="Last_update_ts")
	private Date last_update_ts;

	@Column(name="Parent_folder_id")
	private int parent_folder_id;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Created_by")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Updated_by")
	private Employee employee2;

	public Folder() {
	}

	public int getFolder_id() {
		return this.folder_id;
	}

	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}

	public Date getCreation_ts() {
		return this.creation_ts;
	}

	public void setCreation_ts(Date creation_ts) {
		this.creation_ts = creation_ts;
	}

	public String getFolder_name() {
		return this.folder_name;
	}

	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}

	public int getImmi_type_id() {
		return this.immi_type_id;
	}

	public void setImmi_type_id(int immi_type_id) {
		this.immi_type_id = immi_type_id;
	}

	public Date getLast_update_ts() {
		return this.last_update_ts;
	}

	public void setLast_update_ts(Date last_update_ts) {
		this.last_update_ts = last_update_ts;
	}

	public int getParent_folder_id() {
		return this.parent_folder_id;
	}

	public void setParent_folder_id(int parent_folder_id) {
		this.parent_folder_id = parent_folder_id;
	}

	public Employee getEmployee1() {
		return this.employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Employee getEmployee2() {
		return this.employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}

}