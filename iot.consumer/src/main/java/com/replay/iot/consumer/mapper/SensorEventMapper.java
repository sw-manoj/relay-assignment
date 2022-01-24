package com.replay.iot.consumer.mapper;

import com.replay.iot.consumer.model.Event;
import com.replay.iot.consumer.model.SensorEvent;
import org.springframework.stereotype.Service;

@Service
public class SensorEventMapper {

    public SensorEvent fromEvent(Event event)
    {
        return SensorEvent.builder()
                .sensorId(event.getId())
                .clusterId(event.getClusterId())
                .name(event.getName())
                .timestamp(event.getTimestamp())
                .type(event.getType())
                .value(event.getValue())
                .build();
    }
}
