package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.enums.EventStatus;
import com.nesslabs.nesslabspring.model.Event;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventSpecifications {

    public static Specification<Event> startDateTimeGreaterThanOrEqual(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDateTime"), startDate);
    }

    public static Specification<Event> endDateTimeLessThanOrEqual(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("endDateTime"), endDate);
    }

    public static Specification<Event> hasTags(List<String> tags) {
        return (root, query, criteriaBuilder) ->
                root.get("tagName").in(tags);
    }

    public static Specification<Event> hasCharacteristics(List<String> characteristics) {
        List<String> characteristicsLowerCase = characteristics.stream()
                .map(String::toLowerCase)
                .toList();

//        if(characteristicsLowerCase.contains("pet friendly") && characteristicsLowerCase.contains("kid friendly")) {
//            return (root, query, criteriaBuilder) ->
//                    criteriaBuilder.and(
//                            criteriaBuilder.isTrue(root.get("isPetFriendly")),
//                            criteriaBuilder.isTrue(root.get("isKidFriendly"))
//                    );
//        }
//
//        else if(characteristicsLowerCase.contains("pet friendly")) {
//            return (root, query, criteriaBuilder) ->
//                    criteriaBuilder.isTrue(root.get("isPetFriendly"));
//        }
//
//        else if(characteristicsLowerCase.contains("kid friendly")) {
//            return (root, query, criteriaBuilder) ->
//                    criteriaBuilder.isTrue(root.get("isKidFriendly"));
//        }
//
//        else throw new TagNotFoundException("Wring characteristic input");

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (characteristicsLowerCase.contains("pet friendly")) {
                predicates.add(criteriaBuilder.isTrue(root.get("isPetFriendly")));
            }

            if (characteristicsLowerCase.contains("kid friendly")) {
                predicates.add(criteriaBuilder.isTrue(root.get("isKidFriendly")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

    public static Specification<Event> isFree(boolean isFree) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isFree"), isFree);
    }

    public static Specification<Event> hasEventTypes(List<EventStatus> eventStatus) {
        return (root, query, criteriaBuilder) ->
                root.get("status").in(eventStatus);
    }

    public static Specification<Event> containsText(String searchInput) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + searchInput + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + searchInput + "%"),
                        criteriaBuilder.like(root.get("address"), "%" + searchInput + "%")
                );
    }
}
