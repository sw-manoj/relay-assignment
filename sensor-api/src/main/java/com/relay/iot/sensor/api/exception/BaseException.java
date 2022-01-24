package com.relay.iot.sensor.api.exception;

import com.relay.iot.sensor.api.model.error.BaseErrorMessages;

public class BaseException extends Exception {

    public BaseException(BaseErrorMessages baseErrorMessages) {
        super(baseErrorMessages.getMessage());
    }

    public BaseException(BaseErrorMessages baseErrorMessages, Throwable cause) {
        super(baseErrorMessages.getMessage(), cause);
    }
}
