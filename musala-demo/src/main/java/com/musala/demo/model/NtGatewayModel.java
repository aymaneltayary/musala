package com.musala.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;


/**
 * Gateway Model should be returned by API end point
 * 
 */

public class NtGatewayModel implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;


	private String descriptionName;

	@Pattern(regexp = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",message="Invalid IP adress")
	private String ipv4;

	
	private String serialNumber;

	
	@Size(min=0, max=3,message ="Max 10 devices are allowd" )
	private List<NtDeviceModel> ntDevices;

	public NtGatewayModel() {
	}

	public NtGatewayModel(Long id, String descriptionName,
			@Pattern(regexp = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "Invalid IP adress") String ipv4,
			String serialNumber,
			@Size(min = 0, max = 3, message = "Max 10 devices are allowd") List<NtDeviceModel> ntDevices) {
		super();
		this.id = id;
		this.descriptionName = descriptionName;
		this.ipv4 = ipv4;
		this.serialNumber = serialNumber;
		this.ntDevices = ntDevices;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriptionName() {
		return this.descriptionName;
	}

	public void setDescriptionName(String descriptionName) {
		this.descriptionName = descriptionName;
	}

	public String getIpv4() {
		return this.ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<NtDeviceModel> getNtDevices() {
		return this.ntDevices;
	}

	public void setNtDevices(List<NtDeviceModel> ntDevices) {
		this.ntDevices = ntDevices;
	}

	/**
	 * Helper method to add device to gateway 
	 * @param device
	 */
	public void addDevice(NtDeviceModel device) {
		if (ntDevices==null) {
			ntDevices= new ArrayList<NtDeviceModel>();
			}
		ntDevices.add(device);
	
	}
	
	@Override
	public String toString() {
		return "NtGatewayModel [id=" + id + ", descriptionName=" + descriptionName + ", ipv4=" + ipv4
				+ ", serialNumber=" + serialNumber + ", ntDevices=" + ntDevices + "]";
	}

}