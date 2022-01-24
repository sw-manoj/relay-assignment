package com.replay.iot.consumer.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Event {

	  private Long id;
	  private String type;
	  private String name;
	  private @Nullable Long clusterId;
	  private OffsetDateTime timestamp;
	  private BigDecimal value;
	  
	  public Event()
	  {
		  
	  }
	  
	public Event(Long id, String type, String name, Long clusterId, OffsetDateTime timestamp, BigDecimal value) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.clusterId = clusterId;
		this.timestamp = timestamp;
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getClusterId() {
		return clusterId;
	}
	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}
	public OffsetDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", name=" + name + ", clusterId=" + clusterId + ", timestamp="
				+ timestamp + ", value=" + value + "]";
	}

	  
	  
}
