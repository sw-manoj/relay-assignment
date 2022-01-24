package com.relay.iot.sensor.api.exception;
import com.relay.iot.sensor.api.model.error.BaseErrorMessages;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(BaseErrorMessages.GENERIC_NOT_FOUND);
    }
}
