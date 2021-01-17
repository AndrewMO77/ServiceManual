package com.etteplanmore.servicemanual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.etteplanmore.servicemanual.model.MaintenanceTask;

public interface MaintenanceTaskRepository
		extends JpaRepository<MaintenanceTask, Long>, JpaSpecificationExecutor<MaintenanceTask> {

}
