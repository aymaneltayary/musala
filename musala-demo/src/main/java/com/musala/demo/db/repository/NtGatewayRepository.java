/**
 * 
 */
package com.musala.demo.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.demo.db.entity.NtGateway;

/**
 * @author aeltayary
 *
 */
@Repository
public interface NtGatewayRepository extends JpaRepository<NtGateway, Long> {

public NtGateway findBySerialNumber(String sn);

}
