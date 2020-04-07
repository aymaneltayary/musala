package com.musala.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Device Model should be returned by API end point
 * 
 */

public class NtDeviceModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long uid;

	
	
	private Date creationDate;

	private Boolean state;

	private String vendor;

	
	public NtDeviceModel(Long uid, Date creationDate, Boolean state, String vendor) {
		super();
		this.uid = uid;
		this.creationDate = creationDate;
		this.state = state;
		this.vendor = vendor;
	}

	public NtDeviceModel() {
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

	

	@Override
	public String toString() {
		return "NtDeviceModel [uid=" + uid + ", creationDate=" + creationDate + ", state=" + state + ", vendor="
				+ vendor  + "]";
	}
}