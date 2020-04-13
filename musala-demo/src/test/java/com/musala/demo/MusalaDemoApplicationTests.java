package com.musala.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.musala.demo.common.ErrorCode;
import com.musala.demo.controller.GatewayController;
import com.musala.demo.model.ErrorResponseModel;
import com.musala.demo.model.NtDeviceModel;
import com.musala.demo.model.NtGatewayModel;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"/test-data.sql"}) 
class MusalaDemoApplicationTests {

	@Autowired
	private GatewayController controller;
	
	 @LocalServerPort
	 int randomServerPort;
	
	@Test
	void contextLoads() {
	}

	
	/**
	 * Clear Database 
	 * remove all records 
	 * @param ntGatewayRepo
	 * @param deviceRepo
	 */
	
	@BeforeTestClass
	private void clearDB() {
	}
	
	
	/**
	 * Test AddGateway
	 * Test add new gateway with device details 
	 * @throws Exception
	 */
	
	@Test
	@Sql({"/test-data.sql"}) 
	public void saveGatewayTest() throws Exception {
		List<NtDeviceModel> deviceList= new ArrayList<NtDeviceModel>();
		NtDeviceModel deviceModel=new NtDeviceModel(new Long(77777) , null, true, "vendor_7");
		deviceList.add(deviceModel);
		NtGatewayModel gatwayModel= new NtGatewayModel(null,"gatway_6","122.22.12.34","sn6",deviceList);
		NtGatewayModel result=controller.AddGateway(gatwayModel);
		assertThat(result.getId()!=null);
		

	}
	
	
	/**
	 * Test AddGateway
	 * Test more than 10 record validation
	 * Test add new gateway with more than 10 devices 
	 * @throws Exception
	 */
	
	@Test
	@Sql({"/test-data.sql"}) 
	public void saveGateWayMoreThanTenDevicesTest() throws Exception {
		RestTemplate rest= new RestTemplate();
		List<NtDeviceModel> deviceList= new ArrayList<NtDeviceModel>();
		for(int i=100;i<111;i++) {
		NtDeviceModel deviceModel=new NtDeviceModel(new Long(i) , null, false, "vendor"+i);
		deviceList.add(deviceModel);
		}
		NtGatewayModel gatwayModel= new NtGatewayModel(null,"gatway_8","122.22.12.34","sn8",deviceList);
		ErrorResponseModel resp;
		try {
			resp = rest.postForEntity("http://localhost:"+randomServerPort+"/nt/gateways", gatwayModel, ErrorResponseModel.class).getBody();
		} catch (HttpStatusCodeException httpEx) {
			assertThat(httpEx.getResponseBodyAsString()).contains("Max 10 devices are allowd");
		}
		
		
		

	}

	/**
	 * Test getGatewayBySerial
	 * Test get gateway using serial 
	 * @throws Exception
	 */
	
	@Test
	@Sql({"/test-data.sql"}) 
	public void getGatewayBySerialTest() throws Exception {
		NtGatewayModel result=controller.getGatewayBySerial("sn3");
		assertThat(result.getSerialNumber()).isEqualTo("sn3");

	}

	/**
	 *	Test getAllGateway
	 *	Test get all gateways
	 * @throws Exception
	 */
	
	@Test
	@Sql({"/test-data.sql"}) 
	public void getAllGatewayTest() throws Exception {
		List<NtGatewayModel> result=controller.getAllGateway();
		assertThat(result.size()).isEqualTo(5);

	}


	/**
	 *	Test  Adddevice
	 *	Test get all gateways
	 * @throws Exception
	 */
	
	@Test
	@Sql({"/test-data.sql"}) 
	public void AdddeviceTest() throws Exception {
		NtDeviceModel deviceModel=new NtDeviceModel(new Long(77777) , null, false, "vendor"+77777);
		controller.Adddevice("sn5", deviceModel);
		NtGatewayModel result=controller.getGatewayBySerial("sn5");
		assertThat(result.getNtDevices().size()).isEqualTo(1);

	}
	
	
	
	/**
	 *	Test deleteDevice
	 *	Test delete device 
	 * @throws Exception
	 */
	
	@Test
	@Sql({"/test-data.sql"}) 
	public void deleteDeviceTest() throws Exception {
		controller.deleteDevice(new Long(111111));
		NtGatewayModel result=controller.getGatewayBySerial("sn3");
		assertThat(result.getNtDevices().size()).isEqualTo(2);

	}
	
	
	
}
