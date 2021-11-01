package com.example.visitorservice.persistence;

import com.example.visitorservice.models.VisitorModel;
import org.springframework.data.repository.CrudRepository;

public interface VisitorsRepository extends CrudRepository<VisitorModel, Long> {

}
