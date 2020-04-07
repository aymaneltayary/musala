/**
 * 
 */
package com.musala.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musala.demo.exception.DataNotFoundException;
import com.musala.demo.model.NtDeviceModel;
import com.musala.demo.model.NtGatewayModel;
import com.musala.demo.service.GatewayService;

/**
 * Gateway API end points
 * Gateway rest controller
 * Autowiring GatewayService as serv
 * Delegate all function to serv
 * @author aeltayary
 *
 */
@RestController
@RequestMapping("/nt")
public class GatewayController {
	@Autowired
	private GatewayService serv;

	
	
	/**
	 * return all gateways
	 * url /nt/gateways
	 * @param gateway
	 */
	@GetMapping("/gateways")
	public List<NtGatewayModel> getAllGateway()  {
		return serv.getAllGateways();
		
	}
	

	/**
	 * return gateways in pages
	 * /nt/gateways/{page}
	 * @param pageNumber
	 * @return
	 */
	
	@GetMapping("/gateways/pages/{pageNo}")
	public List<NtGatewayModel> getGatewayPage(@PathVariable Integer pageNo) {
		return serv.getGatewayPage(pageNo, 5, "serialNumber");
	}
	
	
	
	/**
	 * return gateway using serial number 
	 * url /nt/gateways/{sn}
	 * @param gateway
	 * @return
	 */
	@GetMapping("/gateways/{sn}")
	public NtGatewayModel getGatewayBySerial(@PathVariable String sn) {
		return serv.getGatewayBySn(sn);
		
	}

	/**
	 * save a list of of gateways
	 * url /nt/gateways
	 * @param gateway
	 * @return
	 */
	@PostMapping("/gateways")
	@ResponseStatus(code = HttpStatus.CREATED)
	public NtGatewayModel AddGateway(@Valid @RequestBody NtGatewayModel gateway) {
		 return serv.saveGateway(gateway);
		
	}
	
	/**
	 * add device to gateway
	 * url /nt/gateways/{sn}/device
	 * @param sn
	 * @param device
	 * @return
	 */
	@PostMapping("/gateways/{sn}/device")
	@ResponseStatus(code = HttpStatus.CREATED)
	public NtDeviceModel Adddevice(@PathVariable String sn,@RequestBody NtDeviceModel device) throws DataNotFoundException {
		 return serv.addDevice(device,sn);
		
	}
	
	/**
	 * remove device
	 * @param uid
	 */
	@DeleteMapping("/devices/{uid}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteDevice(@PathVariable Long uid) {
		 serv.DeleteDevice(uid);
		
	}
	
}
