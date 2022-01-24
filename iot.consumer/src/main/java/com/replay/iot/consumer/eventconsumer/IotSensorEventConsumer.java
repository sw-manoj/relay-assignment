package com.replay.iot.consumer.eventconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.replay.iot.consumer.controller.SensorEventController;
import com.replay.iot.consumer.model.Event;

@Service
@EnableBinding(Sink.class)
public class IotSensorEventConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(IotSensorEventConsumer.class);

    @Autowired
	SensorEventController SensorEventController;

    @StreamListener(target = Sink.INPUT)
    public void consumeMessage(@Payload Event event) {
        LOG.info("==== Received message: {}", event);
        
        SensorEventController.postEvent(event);
        LOG.info("==== Process Ended for message: {}", event);

    }
}
