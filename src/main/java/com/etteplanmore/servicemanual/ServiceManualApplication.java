package com.etteplanmore.servicemanual;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.etteplanmore.servicemanual.model.FactoryDevice;
import com.etteplanmore.servicemanual.repository.FactoryDeviceRepository;

@SpringBootApplication
public class ServiceManualApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ServiceManualApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final FactoryDeviceRepository factoryDeviceRepository) {
		return (args) -> {
			Logger logger = LoggerFactory.getLogger(ServiceManualApplication.class);
			/*
			 * Values in the CSV are in the following order Name,Year,Type
			 */
			List<FactoryDevice> factoryDevices = new ArrayList<FactoryDevice>();
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader("seeddata.csv"))) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					String[] values = line.split(",");
					try {
						String name = values[0];
						int year = Integer.parseInt(values[1]);
						String type = values[2];
						FactoryDevice factoryDevice = new FactoryDevice(name, year, type);
						logger.debug(factoryDevice.toString());
						factoryDevices.add(new FactoryDevice(name, year, type));
					} catch (NumberFormatException ex) {
						logger.warn("Failed to parse device year from the seed data with name={} value={} type={}",
								values[0], values[1], values[2]);
					}
				}
			}
			factoryDeviceRepository.saveAll(factoryDevices);
		};
	}

}