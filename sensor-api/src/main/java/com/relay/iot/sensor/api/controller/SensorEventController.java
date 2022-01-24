package com.relay.iot.sensor.api.controller;

import com.relay.iot.sensor.api.model.SensorEvent;
import com.relay.iot.sensor.api.model.request.OperationParam;
import com.relay.iot.sensor.api.service.SensorEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorEventController {

	private static final Logger LOG = LoggerFactory.getLogger(SensorEventController.class);

	@Autowired
	SensorEventService sensorEventService;

	@PostMapping("/fetch")
	public ResponseEntity<Iterable<SensorEvent>> fetchSensorEvent(@Valid @RequestBody OperationParam filterParam) throws IllegalAccessException {
		LOG.info("=== Received Request -> {}", filterParam);
		return new ResponseEntity<>(sensorEventService.fetchSensorEvent(filterParam), HttpStatus.OK);
	}

	@PostMapping("/fetch/min")
	public ResponseEntity<OperationParam> minSensorEvent(@Valid  @RequestBody OperationParam filterParam) throws IllegalAccessException {
		LOG.info("=== Received Request to fetch Min sensor value -> {}", filterParam);
		return new ResponseEntity<>(sensorEventService.minSensorEvent(filterParam), HttpStatus.OK);

	}

	@PostMapping("/sensorDetail/max")
	public ResponseEntity<OperationParam> maxSensorEvent(@Valid @RequestBody OperationParam filterParam) throws IllegalAccessException {
		LOG.info("=== Received Request to fetch Max sensor value -> {}", filterParam);
		return new ResponseEntity<>(sensorEventService.maxSensorEvent(filterParam), HttpStatus.OK);
	}

	@PostMapping("/sensorDetail/avg")
	public ResponseEntity<OperationParam> avgSensorEvent(@Valid @RequestBody OperationParam filterParam) throws IllegalAccessException {
		LOG.info("=== Received Request to fetch Avg sensor value -> {}", filterParam);
		return new ResponseEntity<>(sensorEventService.avgSensorEvent(filterParam), HttpStatus.OK);

	}
}
