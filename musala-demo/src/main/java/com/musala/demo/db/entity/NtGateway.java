package com.musala.demo.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.List;


/**
 * The persistent class for the nt_gateway database table.
 * 
 */
@Entity
@Table(name="nt_gateway")
@NamedQuery(name="NtGateway.findAll", query="SELECT n FROM NtGateway n")
public class NtGateway implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="description_name")
	private String descriptionName;

	private String ipv4;

	@Column(name="serial_number")
	private String serialNumber;

	//bi-directional many-to-one association to NtDevice
	@OneToMany(mappedBy="ntGateway",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<NtDevice> ntDevices;

	public NtGateway() {
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

	public List<NtDevice> getNtDevices() {
		return this.ntDevices;
	}

	public void setNtDevices(List<NtDevice> ntDevices) {
		this.ntDevices = ntDevices;
	}

	public NtDevice addNtDevice(NtDevice ntDevice) {
		getNtDevices().add(ntDevice);
		ntDevice.setNtGateway(this);

		return ntDevice;
	}

	public NtDevice removeNtDevice(NtDevice ntDevice) {
		getNtDevices().remove(ntDevice);
		ntDevice.setNtGateway(null);

		return ntDevice;
	}

}