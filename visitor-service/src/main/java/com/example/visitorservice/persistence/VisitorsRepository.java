package com.example.visitorservice.persistence;

import com.example.visitorservice.models.VisitorModel;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface VisitorsRepository extends JpaRepository<VisitorModel, Long>, JpaSpecificationExecutor<VisitorModel> {

    // TODO Create a separate component (@Component) with these static methods
//    static Specification<VisitorModel> containsFirstName(String firstName) {
//        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("firstName"), "%" + firstName + "%");
//    }
//
//    static Specification<VisitorModel> containsLastName(String lastName) {
//        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("lastName"), "%" + lastName + "%");
//    }
//
//    static Specification<VisitorModel> containsEmail(String email) {
//        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("email"), "%" + email + "%");
//    }
//
//    static Specification<VisitorModel> containsPhoneNumber(String phoneNumber) {
//        return (visitorModel, cq, cb) -> cb.like(visitorModel.get("phoneNumber"), "%" + phoneNumber + "%");
//    }
}
