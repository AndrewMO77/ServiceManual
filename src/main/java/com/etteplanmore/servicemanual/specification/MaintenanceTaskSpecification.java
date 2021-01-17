package com.etteplanmore.servicemanual.specification;

import org.springframework.data.jpa.domain.Specification;

import com.etteplanmore.servicemanual.model.MaintenanceTask;

public class MaintenanceTaskSpecification {

	public static Specification<MaintenanceTask> subjectContains(String searchValue) {
	    return (maintenanceTask, cq, cb) -> cb.like(maintenanceTask.get("subject"), "%"+searchValue+"%");
	}
}
