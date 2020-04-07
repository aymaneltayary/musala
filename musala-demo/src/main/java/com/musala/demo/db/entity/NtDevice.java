package com.musala.demo.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;


/**
 * The persistent class for the nt_device database table.
 * 
 */
@Entity
@Table(name="nt_device")
@NamedQuery(name="NtDevice.findAll", query="SELECT n FROM NtDevice n")
public class NtDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private Long uid;

	@Column(name="creation_date")
	
	@CreationTimestamp
	private Date creationDate;

	private Boolean state;

	private String vendor;

	//bi-directional many-to-one association to NtGateway
	@ManyToOne
	@JoinColumn(name="gateway_id")
	private NtGateway ntGateway;

	public NtDevice() {
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public NtGateway getNtGateway() {
		return this.ntGateway;
	}

	public void setNtGateway(NtGateway ntGateway) {
		this.ntGateway = ntGateway;
	}

}