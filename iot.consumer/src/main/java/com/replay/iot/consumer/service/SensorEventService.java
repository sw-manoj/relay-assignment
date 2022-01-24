package com.replay.iot.consumer.service;

import com.replay.iot.consumer.controller.SensorEventController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.replay.iot.consumer.model.SensorEvent;
import com.replay.iot.consumer.repository.SensorEventRepository;

@Service
public class SensorEventService {
	private static final Logger LOG = LoggerFactory.getLogger(SensorEventService.class);

	@Autowired
	SensorEventRepository sensorEventRepository;
	
	public void saveSensor(SensorEvent event)
	{
		LOG.info("Persisting the  message ");
		sensorEventRepository.save(event);
		LOG.info("Saved the  message to DB");
	}
}
