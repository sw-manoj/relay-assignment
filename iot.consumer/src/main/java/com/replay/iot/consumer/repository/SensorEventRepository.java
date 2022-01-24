package com.replay.iot.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.replay.iot.consumer.model.SensorEvent;

@Repository
public interface SensorEventRepository extends JpaRepository<SensorEvent, Long>{

}
