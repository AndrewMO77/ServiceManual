package com.etteplanmore.servicemanual.factorydevice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    
    public FactoryDevice(String name, int year, String type) {
    	this.name = name;
    	this.year = year;
    	this.type = type;
    }

}