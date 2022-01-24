package com.relay.iot.sensor.api.service;

import com.relay.iot.sensor.api.model.SensorEvent;
import com.relay.iot.sensor.api.model.request.OperationParam;
import com.relay.iot.sensor.api.repository.SensorEventEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorEventService {
    private static final Logger LOG = LoggerFactory.getLogger(SensorEventService.class);

    @Autowired
    SensorEventEntityRepository sensorEventEntityRepository;

    public Iterable<SensorEvent> fetchSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Triggering execution to fetch SensorEvent ");
        return sensorEventEntityRepository.fetchSensorEvent(operationParam);
    }

    public OperationParam minSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Triggering execution to fetch Min SensorEvent Value");
        return sensorEventEntityRepository.minSensorEvent(operationParam);
    }

    public OperationParam maxSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Triggering execution to fetch Max SensorEvent Value");
        return sensorEventEntityRepository.maxSensorEvent(operationParam);
    }

    public OperationParam avgSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Triggering execution to fetch Avg SensorEvent Value");
        return sensorEventEntityRepository.avgSensorEvent(operationParam);
    }
}
