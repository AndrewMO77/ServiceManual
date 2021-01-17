package com.etteplanmore.servicemanual.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Table(name = "MaintenanceTask")
@Entity
public class MaintenanceTask {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	String subject;
	LocalDateTime completedTimestamp;
	String description;

	@Enumerated(EnumType.STRING)
	UrgencyCategoryEnum urgencyCategory;

	@Enumerated(EnumType.STRING)
	TaskStatusEnum taskStatus;
	
	@JsonManagedReference
	@ManyToOne
    @JoinColumn(name="device", nullable=false)
	FactoryDevice factoryDevice;

}
