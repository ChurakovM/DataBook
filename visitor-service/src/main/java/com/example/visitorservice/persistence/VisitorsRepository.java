package com.example.visitorservice.persistence;

import com.example.visitorservice.models.VisitorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitorsRepository extends JpaRepository<VisitorModel, Long>, JpaSpecificationExecutor<VisitorModel> {

}
