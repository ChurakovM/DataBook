package com.example.visitorservice.persistence;

import com.example.visitorservice.models.VisitorModel;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class SpecificationUtils {

    public static Specification<VisitorModel> containsFirstName(String firstName) {
        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<VisitorModel> containsLastName(String lastName) {
        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<VisitorModel> containsEmail(String email) {
        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("email"), "%" + email + "%");
    }

    public static Specification<VisitorModel> containsPhoneNumber(String phoneNumber) {
        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("phoneNumber"), "%" + phoneNumber + "%");
    }
}
