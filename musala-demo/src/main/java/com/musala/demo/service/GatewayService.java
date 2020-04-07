/**
 * 
 */
package com.musala.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.musala.demo.db.entity.NtDevice;
import com.musala.demo.db.entity.NtGateway;
import com.musala.demo.db.repository.NtDeviceRepository;
import com.musala.demo.db.repository.NtGatewayRepository;
import com.musala.demo.exception.DataNotFoundException;
import com.musala.demo.model.NtDeviceModel;
import com.musala.demo.model.NtGatewayModel;

/**
 * Gateway business layer Delegate Database operation to NtGatewayRepository
 * BeanUtils is used to copy entity to model and vice versa
 * 
 * @author aeltayary
 *
 */
@Service
public class GatewayService {

	private static final Logger log= LoggerFactory.getLogger(GatewayService.class);
	
	@Autowired
	NtGatewayRepository gatwayRepo;

	@Autowired
	NtDeviceRepository deviceRepo;

	/**
	 * return all gateways
	 * 
	 * @return
	 * @throws NoDataFoundException 
	 */
	public List<NtGatewayModel> getAllGateways()  {
		
		log.debug("getAllGateways has been started");
		List<NtGateway> entityList = gatwayRepo.findAll();
		List<NtGatewayModel> result = new ArrayList<NtGatewayModel>();
		entityList.stream().forEach(entity -> {
			NtGatewayModel model = populateModel(entity);
			result.add(model);
		});
		log.debug("getAllGateways has been returned");
		return result;
	}

	/**
	 * return page of gateways Page size is 5
	 * 
	 * @return
	 */
	public List<NtGatewayModel> getGatewayPage(Integer pageNumber, Integer pageSize, String sortBy) {
		log.debug("getGatewayPage has been started  , pageNumber="+pageNumber);
		Pageable page = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
		Page<NtGateway> pageResult = gatwayRepo.findAll(page);
		List<NtGatewayModel> result = new ArrayList<NtGatewayModel>();
		pageResult.getContent().stream().forEach(entity -> {
			NtGatewayModel model = populateModel(entity);
			result.add(model);
		});
		log.debug("getGatewayPage has been returned");
		return result;
	}

	/**
	 * Return gateway using serial number
	 * 
	 * @param sn
	 * @return
	 */
	public NtGatewayModel getGatewayBySn(String sn) {
		log.debug("getGatewayBySn has been started  , gateway serial="+sn);
		NtGateway entity = gatwayRepo.findBySerialNumber(sn);
		NtGatewayModel model = (entity==null)?null:populateModel(entity);
		log.debug("getGatewayPage has been returned");
		return model;
	}

	/**
	 * Save gateway with its device details
	 * 
	 * @param gateway
	 */
	public NtGatewayModel saveGateway(NtGatewayModel gateway) {
		log.debug("saveGateway has been started  , gateway="+ gateway);
		NtGateway gatewayEntity = populateEntity(gateway);
		gatewayEntity = gatwayRepo.save(gatewayEntity);
		gateway = populateModel(gatewayEntity);
		log.debug("saveGateway has been returned");
		return gateway;
	}

	/**
	 * Add device to gateway
	 * 
	 * @param device
	 * @param sn
	 * @return
	 */
	public NtDeviceModel addDevice(NtDeviceModel device, String sn) throws DataNotFoundException{
		log.debug("addDevice has been started  , gateway serial=" +sn +",device= "+device);
		NtDevice deviceEntity = new NtDevice();
		BeanUtils.copyProperties(device, deviceEntity);
		NtGateway gatewayEntity = gatwayRepo.findBySerialNumber(sn);
		 if (gatewayEntity ==null) { 
			 log.error("gateway with serial number="+sn+"is not found");
			 throw new DataNotFoundException("parent gateway was not found");
			 
		 }
		deviceEntity.setNtGateway(gatewayEntity);
		deviceEntity=deviceRepo.saveAndFlush(deviceEntity);
		BeanUtils.copyProperties(deviceEntity,device);
		log.debug("addDevice has been returned");
		return device;
	}

	/**
	 * delete device
	 * 
	 * @param uid
	 */
	@Transactional
	public void DeleteDevice(Long uid) {
		deviceRepo.deleteByUid(uid);

	}

	/**
	 * Copy entity to model
	 * 
	 * @param entity
	 * @return
	 */
	private NtGatewayModel populateModel(NtGateway entity) {
		log.debug("Populate the model should be returned ");
		NtGatewayModel model = new NtGatewayModel();
		BeanUtils.copyProperties(entity, model, "ntDevices");
		List<NtDeviceModel> deviceList = new ArrayList<NtDeviceModel>();
		entity.getNtDevices().stream().forEach((deviceEntity) -> {
			NtDeviceModel deviceModel = new NtDeviceModel();
			BeanUtils.copyProperties(deviceEntity, deviceModel);
			deviceList.add(deviceModel);
		});
		model.setNtDevices(deviceList);
		return model;
	}

	/**
	 * Copy model to Entity
	 * 
	 * @param gateway
	 * @param gatewayEntity
	 */
	private NtGateway populateEntity(NtGatewayModel gateway) {
		log.debug("Populate DB entities");
		List<NtDevice> deviceList = new ArrayList<NtDevice>();
		NtGateway gatewayEntity = new NtGateway();
		BeanUtils.copyProperties(gateway, gatewayEntity, "ntDevices", "id");

		gateway.getNtDevices().stream().forEach(deviceModel -> {
			NtDevice device = new NtDevice();
			BeanUtils.copyProperties(deviceModel, device);
			deviceList.add(device);
			device.setNtGateway(gatewayEntity);
		});
		gatewayEntity.setNtDevices(deviceList);
		return gatewayEntity;
	}

}
