package com.etteplanmore.servicemanual.controller;

import org.springframework.web.bind.annotation.RestController;

import com.etteplanmore.servicemanual.exception.FactoryDeviceNotFoundException;
import com.etteplanmore.servicemanual.model.FactoryDevice;
import com.etteplanmore.servicemanual.repository.FactoryDeviceRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FactoryDeviceController {

    private final FactoryDeviceRepository repository;

    FactoryDeviceController(FactoryDeviceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/factorydevices")
    List<FactoryDevice> all() {
        return repository.findAll();
    }

    @GetMapping("/factorydevices/{id}")
    FactoryDevice one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new FactoryDeviceNotFoundException(id));
    }
}