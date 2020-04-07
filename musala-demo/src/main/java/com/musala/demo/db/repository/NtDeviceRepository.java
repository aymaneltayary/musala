package com.musala.demo.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.musala.demo.db.entity.NtDevice;

@Repository
public interface NtDeviceRepository extends JpaRepository<NtDevice, Long> {

	@Modifying
	@Query("delete from NtDevice device where device.uid=:uid")
	public void deleteByUid(Long uid);	
}
