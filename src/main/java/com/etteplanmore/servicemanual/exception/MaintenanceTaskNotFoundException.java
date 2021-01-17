package com.etteplanmore.servicemanual.exception;

public class MaintenanceTaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9213527152182132717L;

	public MaintenanceTaskNotFoundException(Long id) {
        super("Could not find maintenance task by id=" + id);
    }
}
