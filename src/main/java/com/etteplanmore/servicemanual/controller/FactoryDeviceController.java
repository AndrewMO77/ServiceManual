package com.etteplanmore.servicemanual.controller;

import org.springframework.web.bind.annotation.RestController;

import com.etteplanmore.servicemanual.exception.FactoryDeviceNotFoundException;
import com.etteplanmore.servicemanual.model.FactoryDevice;
import com.etteplanmore.servicemanual.model.MaintenanceTask;
import com.etteplanmore.servicemanual.repository.FactoryDeviceRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(ControllerHelper.apiBaseUrl + "/device")
public class FactoryDeviceController {

	@Autowired
    FactoryDeviceRepository repository;

    @Operation(summary = "Get all devices")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Devices found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Devices not found", content = @Content) })
	@GetMapping()
    List<FactoryDevice> getAllDevices() {
        return repository.findAll();
    }

    @Operation(summary = "Get device by id")
	@Parameter(name = "deviceId", in = ParameterIn.PATH, description = "Device identifier", required = true)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Device found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceTask.class)) }),
			@ApiResponse(responseCode = "404", description = "Device not found", content = @Content) })
	@GetMapping("/{deviceId}")
    FactoryDevice getDevice(@PathVariable Long deviceId) {
        return repository.findById(deviceId)
            .orElseThrow(() -> new FactoryDeviceNotFoundException(deviceId));
    }
}