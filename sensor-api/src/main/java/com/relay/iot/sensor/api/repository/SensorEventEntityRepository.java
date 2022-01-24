package com.relay.iot.sensor.api.repository;

import com.relay.iot.sensor.api.model.SensorEvent;
import com.relay.iot.sensor.api.model.request.OperationParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.function.BiFunction;

@Repository
public class SensorEventEntityRepository  {
    private static final Logger LOG = LoggerFactory.getLogger(SensorEventEntityRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<SensorEvent> fetchSensorEvent(OperationParam operationParam) throws IllegalAccessException {

        LOG.info(" Generating the Search criteria to fetch event");
        //Retrieveing the root query from criteria builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SensorEvent> query = criteriaBuilder.createQuery(SensorEvent.class);
        Root<SensorEvent> root = query.from(SensorEvent.class);

        OperationQueryPredicateGenerator<OperationParam> predicateGenerator = new OperationQueryPredicateGenerator(criteriaBuilder, root, operationParam);
        query.where(predicateGenerator.composePredicate());

        LOG.info(" Executing the query by search criteria ");
        return entityManager.createQuery(query).getResultList();
    }

    public OperationParam minSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Generating the Search criteria to fetch min value sensor event");
        return applyAggOperation(operationParam, (cb, r) -> cb.min(r.get("value")));
    }

    public OperationParam maxSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Generating the Search criteria to fetch max value sensor event");
        return applyAggOperation(operationParam, (cb, r) -> cb.max(r.get("value")));
    }

    public OperationParam avgSensorEvent(OperationParam operationParam) throws IllegalAccessException {
        LOG.info(" Generating the Search criteria to fetch avg value sensor event");
        return applyAggOperation(operationParam, (cb, r) -> cb.avg(r.get("value")));
    }

    private OperationParam applyAggOperation(OperationParam operationParam, BiFunction<CriteriaBuilder, Root, Expression> aggFunc) throws IllegalAccessException {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = criteriaBuilder.createQuery(Double.class);
        Root<SensorEvent> root = query.from(SensorEvent.class);


        OperationQueryPredicateGenerator<OperationParam> predicateGenerator = new OperationQueryPredicateGenerator(criteriaBuilder, root, operationParam);

        query.select(aggFunc.apply(criteriaBuilder, root)).
                where(predicateGenerator.composePredicate());

        Double result = entityManager.createQuery(query).getSingleResult();
        operationParam.setValue(result);
        return operationParam;
    }
}
