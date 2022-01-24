package com.replay.iot.consumer.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.*;

import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = @Index(name = "timestamp_index", columnList = "timestamp"))
public class SensorEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long sensorId;
	private String type;
	private String name;
	private @Nullable Long clusterId;
	private OffsetDateTime timestamp;
	private BigDecimal value;
	
}
