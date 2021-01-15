package com.etteplanmore.servicemanual.controller.dto;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.etteplanmore.servicemanual.model.TaskStatusEnum;
import com.etteplanmore.servicemanual.model.UrgencyCategoryEnum;

import lombok.Data;

@Data
public class MaintenanceTaskDTO {

	String subject;
	LocalDateTime completedTimestamp;
	String description;

	@Enumerated(EnumType.STRING)
	UrgencyCategoryEnum urgencyCategory;

	@Enumerated(EnumType.STRING)
	TaskStatusEnum taskStatus;
}
