package com.relay.iot.sensor.api.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.relay.iot.sensor.api.operation.annotation.GreaterThanEqualTo;
import com.relay.iot.sensor.api.operation.annotation.In;
import com.relay.iot.sensor.api.operation.annotation.LessThanEqualTo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationParam {

    @GreaterThanEqualTo("timestamp")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NonNull
    private OffsetDateTime fromDate;

    @LessThanEqualTo("timestamp")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "{mandatory.request.type}")
    private OffsetDateTime toDate;

    @In("type")
    private List<String> eventType;
    @In
    private List<String> clusterId;
    private Object value;
}
