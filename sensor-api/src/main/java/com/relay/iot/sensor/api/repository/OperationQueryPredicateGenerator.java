package com.relay.iot.sensor.api.repository;

import com.relay.iot.sensor.api.operation.annotation.Equal;
import com.relay.iot.sensor.api.operation.annotation.GreaterThanEqualTo;
import com.relay.iot.sensor.api.operation.annotation.In;
import com.relay.iot.sensor.api.operation.annotation.LessThanEqualTo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OperationQueryPredicateGenerator<T> {
    private static final Logger LOG = LoggerFactory.getLogger(OperationQueryPredicateGenerator.class);

    CriteriaBuilder criteriaBuilder;
    Root root;
    T operationParam;

    public Predicate composePredicate() throws IllegalAccessException {
        Predicate predicateComposed = criteriaBuilder.conjunction();
        for(Predicate predicate : generateOperationPredicate(criteriaBuilder, root, operationParam))
        {
            predicateComposed = criteriaBuilder.and(predicateComposed, predicate);
        }
        return predicateComposed;
    }

    private List<Predicate> generateOperationPredicate(CriteriaBuilder criteriaBuilder, Root root, T operationParam) throws IllegalAccessException {
        List<Predicate>  operationPredicateList = new ArrayList<>();
        for (Field field : operationParam.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Equal.class) && field.get(operationParam) != null)
            {
                operationPredicateList.add(equalPredicate(criteriaBuilder, root, getPredicateTargetFieldname(field.getAnnotation(Equal.class).value(), field.getName()), field.get(operationParam)));
            } else if (field.isAnnotationPresent(GreaterThanEqualTo.class) && field.get(operationParam) != null && field.get(operationParam) instanceof  Comparable) {
                operationPredicateList.add(greaterThanEqualToPredicate(criteriaBuilder, root, getPredicateTargetFieldname(field.getAnnotation(GreaterThanEqualTo.class).value(), field.getName()), (Comparable) field.get(operationParam)));
            }
            else if (field.isAnnotationPresent(LessThanEqualTo.class) && field.get(operationParam)!= null && field.get(operationParam) instanceof  Comparable) {
                operationPredicateList.add(lessThanEqualToPredicate(criteriaBuilder, root, getPredicateTargetFieldname(field.getAnnotation(LessThanEqualTo.class).value(), field.getName()), (Comparable) field.get(operationParam)));
            }
            else if (field.isAnnotationPresent(In.class) && field.get(operationParam)!= null && field.get(operationParam) instanceof  List) {
                operationPredicateList.add(inPredicate(criteriaBuilder, root, getPredicateTargetFieldname(field.getAnnotation(In.class).value(), field.getName()), (List) field.get(operationParam)));
            }
        }

        return operationPredicateList;
    }

    private String getPredicateTargetFieldname(String annotationString, String orgFieldName)
    {
        return annotationString.isBlank() ? orgFieldName : annotationString;
    }

    private Predicate equalPredicate(CriteriaBuilder criteriaBuilder, Root root,String key, Object value)
    {
        return criteriaBuilder.equal(root.get(key), value);
    }

    private Predicate greaterThanEqualToPredicate(CriteriaBuilder criteriaBuilder, Root root,String key, Comparable value)
    {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(key), value);
    }

    private Predicate lessThanEqualToPredicate(CriteriaBuilder criteriaBuilder, Root root,String key, Comparable value)
    {
        return criteriaBuilder.lessThanOrEqualTo(root.get(key), value);
    }

    private Predicate inPredicate(CriteriaBuilder criteriaBuilder, Root root,String key, List value)
    {
        return root.get(key).in(value);
    }
}
