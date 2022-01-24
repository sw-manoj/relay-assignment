package com.replay.iot.consumer.controller;

import com.replay.iot.consumer.mapper.SensorEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.replay.iot.consumer.model.Event;
import com.replay.iot.consumer.model.SensorEvent;
import com.replay.iot.consumer.service.SensorEventService;

@Service
public class SensorEventController {

    private static final Logger LOG = LoggerFactory.getLogger(SensorEventController.class);

    @Autowired
    SensorEventService sensorEventService;

    @Autowired
    SensorEventMapper sensorEventMapper;

    public void postEvent(Event event) {
        LOG.info("==== Consumed Event: {}", event);
        sensorEventService.saveSensor(sensorEventMapper.fromEvent(event));
        LOG.info("==== Completed processing Event ");

    }
}
