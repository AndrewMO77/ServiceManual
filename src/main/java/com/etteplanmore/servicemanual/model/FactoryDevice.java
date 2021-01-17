package com.etteplanmore.servicemanual.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Table(name = "FactoryDevice")
@Entity
public class FactoryDevice implements Serializable {

    private static final long serialVersionUID = 6509908567989139338L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private int year;
    private String type;
    
    @JsonBackReference
    @OneToMany(mappedBy="factoryDevice", cascade = CascadeType.ALL)
    private Set<MaintenanceTask> maintenanceTasks;
    
    public FactoryDevice() {
    }
    
    public FactoryDevice(String name, int year, String type) {
    	this.name = name;
    	this.year = year;
    	this.type = type;
    }
    
    public void addMaintenanceTask(MaintenanceTask maintenanceTask) {
    	this.maintenanceTasks.add(maintenanceTask);
    }

}