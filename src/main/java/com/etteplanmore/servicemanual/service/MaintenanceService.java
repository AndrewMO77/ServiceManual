package com.etteplanmore.servicemanual.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.etteplanmore.servicemanual.controller.request.MaintenanceTaskRequest;
import com.etteplanmore.servicemanual.exception.FactoryDeviceNotFoundException;
import com.etteplanmore.servicemanual.exception.MaintenanceTaskNotFoundException;
import com.etteplanmore.servicemanual.model.FactoryDevice;
import com.etteplanmore.servicemanual.model.MaintenanceTask;
import com.etteplanmore.servicemanual.repository.FactoryDeviceRepository;
import com.etteplanmore.servicemanual.repository.MaintenanceTaskRepository;
import com.etteplanmore.servicemanual.specification.MaintenanceTaskSpecification;

import org.springframework.data.jpa.domain.Specification;

@Service
public class MaintenanceService {

	@Autowired
	MaintenanceTaskRepository maintenanceTaskRepository;

	@Autowired
	FactoryDeviceRepository factoryDeviceRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Create new maintenance task
	 * 
	 * @param maintenanceTask New maintenance task entity 
	 * @return Created maintenance task 
	 * @throws
	 **/
	public MaintenanceTask createMaintenanceTask(MaintenanceTask maintenanceTask) {
		return maintenanceTaskRepository.saveAndFlush(maintenanceTask);

	}

	/**
	 * Update maintenance task
	 * 
	 * @param maintenanceTask New maintenance task entity 
	 * @return Created maintenance task @throws
	 **/
	public MaintenanceTask updateMaintenanceTask(Long maintenanceTaskId, MaintenanceTask maintenanceTaskUpdates) {
		MaintenanceTask maintenanceTask = this.maintenanceTaskRepository.getOne(maintenanceTaskId);
		maintenanceTask.setCompletedTimestamp(maintenanceTaskUpdates.getCompletedTimestamp());
		maintenanceTask.setDescription(maintenanceTaskUpdates.getDescription());
		maintenanceTask.setSubject(maintenanceTaskUpdates.getSubject());
		maintenanceTask.setTaskStatus(maintenanceTaskUpdates.getTaskStatus());
		maintenanceTask.setUrgencyCategory(maintenanceTaskUpdates.getUrgencyCategory());
		return maintenanceTaskRepository.saveAndFlush(maintenanceTask);
	}

	/**
	 * Get all maintenance tasks order by priority and timestamp of task completed
	 * in descending order. Filter returned value by subject.
	 * 
	 * @param subjectSearchValue Search if task subject contains this value
	 * @return List of maintenance tasks or empty list if nothing found
	 **/
	public List<MaintenanceTask> getAllMaintenanceTasks(String subjectSearchValue) {
		return maintenanceTaskRepository.findAll(
				Specification.where(MaintenanceTaskSpecification.subjectContains(subjectSearchValue)),
				Sort.by(Arrays.asList(Order.by("urgencyCategory"), Order.desc("completedTimestamp"))));
	}

	/**
	 * Get single maintenance task by id
	 * 
	 * @param maintenanceTaskId Identifier of the entity
	 * @return Single maintenance task
	 * @throws MaintenanceTaskNotFound Didn't find the entity by given id
	 **/
	public MaintenanceTask getMaintenanceTaskById(Long maintenanceTaskId) {
		return maintenanceTaskRepository.findById(maintenanceTaskId)
				.orElseThrow(() -> new MaintenanceTaskNotFoundException(maintenanceTaskId));
	}

	/**
	 * Delete single maintenance task by id
	 * 
	 * @param maintenanceTaskId Identifier of the entity
	 * @return
	 * @throws MaintenanceTaskNotFound Didn't find the entity by given id
	 **/
	public void deleteMaintenanceTaskById(Long maintenanceTaskId) {
		MaintenanceTask maintenanceTask = this.getMaintenanceTaskById(maintenanceTaskId);
		maintenanceTaskRepository.delete(maintenanceTask);
	}

	/**
	 * Convert MaintenanceTaskRequest to MaintenanceTask entity
	 * 
	 * @param maintenanceTaskRequest Request to be converted
	 * @return maintenanceTask Converted entity
	 * @throws FactoryDeviceNotFoundException Didn't find the FactoryDevice entity
	 **/
	public MaintenanceTask convertToEntity(MaintenanceTaskRequest maintenanceTaskRequest) {
		MaintenanceTask maintenanceTask = modelMapper.map(maintenanceTaskRequest, MaintenanceTask.class);
		FactoryDevice factoryDevice = factoryDeviceRepository.findById(maintenanceTaskRequest.getFactoryDeviceId())
				.orElseThrow(() -> new FactoryDeviceNotFoundException(maintenanceTaskRequest.getFactoryDeviceId()));
		maintenanceTask.setFactoryDevice(factoryDevice);
		return maintenanceTask;
	}

}
