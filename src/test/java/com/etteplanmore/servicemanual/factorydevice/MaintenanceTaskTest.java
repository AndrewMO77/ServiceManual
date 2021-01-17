package com.etteplanmore.servicemanual.factorydevice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.etteplanmore.servicemanual.controller.request.MaintenanceTaskRequest;
import com.etteplanmore.servicemanual.exception.MaintenanceTaskNotFoundException;
import com.etteplanmore.servicemanual.model.TaskStatusEnum;
import com.etteplanmore.servicemanual.model.UrgencyCategoryEnum;
import com.etteplanmore.servicemanual.service.MaintenanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MaintenanceTaskTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	MaintenanceService maintenanceService;

	@Test
	public void getAllMaintenanceTasks() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/maintenance").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test(expected = MaintenanceTaskNotFoundException.class)
	public void deleteNonExistingEntity() {
		this.maintenanceService.deleteMaintenanceTaskById(6l);
	}

	@Test
	public void createNewTask() throws Exception {
		MaintenanceTaskRequest maintenanceTaskRequest = new MaintenanceTaskRequest();
		maintenanceTaskRequest.setCompletedTimestamp(LocalDateTime.now());
		maintenanceTaskRequest.setDescription("test description");
		maintenanceTaskRequest.setFactoryDeviceId(1l);
		maintenanceTaskRequest.setSubject("test subject");
		maintenanceTaskRequest.setTaskStatus(TaskStatusEnum.OPEN);
		maintenanceTaskRequest.setUrgencyCategory(UrgencyCategoryEnum.IMPORTANT);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestBody = ow.writeValueAsString(maintenanceTaskRequest);
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/maintenance").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().isOk());
	}
}