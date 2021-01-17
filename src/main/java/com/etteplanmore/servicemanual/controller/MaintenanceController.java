package com.etteplanmore.servicemanual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etteplanmore.servicemanual.controller.request.MaintenanceTaskRequest;
import com.etteplanmore.servicemanual.model.MaintenanceTask;
import com.etteplanmore.servicemanual.service.MaintenanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(ControllerHelper.apiBaseUrl + "/maintenance")
public class MaintenanceController {

	@Autowired
	MaintenanceService maintenanceService;

	@Operation(summary = "Create new maintenance task")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Task creation ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceTask.class)) }),
			@ApiResponse(responseCode = "404", description = "Facotry device not found", content = @Content) })
	@PostMapping("")
	public ResponseEntity<MaintenanceTask> createMaintenanceTask(
			@RequestBody MaintenanceTaskRequest maintenanceTaskRequest) {
		return ResponseEntity.ok(
				maintenanceService.createMaintenanceTask(maintenanceService.convertToEntity(maintenanceTaskRequest)));
	}

	@Operation(summary = "Update maintenance task")
	@Parameter(name = "taskId", in = ParameterIn.PATH, description = "Maintenance task identifier", required = true)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Task updated ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceTask.class)) }),
			@ApiResponse(responseCode = "404", description = "Task not found", content = @Content) })
	@PutMapping("/{taskId}")
	public ResponseEntity<MaintenanceTask> updateMaintenanceTask(@PathVariable(name = "taskId") Long maintenanceTaskId,
			@RequestBody MaintenanceTaskRequest maintenanceTaskRequest) {
		return ResponseEntity.ok(maintenanceService.updateMaintenanceTask(maintenanceTaskId,
				maintenanceService.convertToEntity(maintenanceTaskRequest)));
	}

	@Operation(summary = "Delete maintenance task")
	@Parameter(name = "taskId", in = ParameterIn.PATH, description = "Maintenance task identifier", required = true)
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Task deleted", content = @Content),
			@ApiResponse(responseCode = "404", description = "Task not found", content = @Content) })
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteMaintenanceTask(@PathVariable(name = "taskId") Long maintenanceTaskId) {
		maintenanceService.deleteMaintenanceTaskById(maintenanceTaskId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Get maintenance task")
	@Parameter(name = "taskId", in = ParameterIn.PATH, description = "Maintenance task identifier", required = true)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Task found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceTask.class)) }),
			@ApiResponse(responseCode = "404", description = "Task not found", content = @Content) })
	@GetMapping("/{taskId}")
	public ResponseEntity<MaintenanceTask> getMaintenanceTask(@PathVariable(name = "taskId") Long maintenanceTaskId) {
		return ResponseEntity.ok(maintenanceService.getMaintenanceTaskById(maintenanceTaskId));
	}

	@Operation(summary = "Get all maintenance tasks")
	@Parameter(name = "subject", in = ParameterIn.QUERY, description = "Subject search value", required = true)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }) })
	@GetMapping()
	public ResponseEntity<List<MaintenanceTask>> getAllMaintenanceTasks(
			@RequestParam(name = "subject", defaultValue = "", required = false) String subjectSearchValue) {
		return ResponseEntity.ok(maintenanceService.getAllMaintenanceTasks(subjectSearchValue));
	}

}
