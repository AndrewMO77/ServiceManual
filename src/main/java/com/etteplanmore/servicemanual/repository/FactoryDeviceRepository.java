package com.etteplanmore.servicemanual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etteplanmore.servicemanual.model.FactoryDevice;

public interface FactoryDeviceRepository extends JpaRepository<FactoryDevice, Long> {
    
}