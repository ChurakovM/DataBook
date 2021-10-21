package com.example.userservice.services;

import com.example.userservice.models.VisitorModel;
import com.example.userservice.exceptions.VisitorsNotFoundException;
import com.example.userservice.mappers.VisitorMapper;
import com.example.userservice.models.VisitorContactQueryParameters;
import com.example.userservice.persistence.VisitorPersistenceService;
import com.example.userservice.requests.PostVisitorRequest;
import com.example.userservice.requests.PutVisitorRequest;
import com.example.userservice.utils.Utils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorService {

    private final VisitorPersistenceService visitorPersistenceService;
    private final VisitorMapper visitorMapper;

    public VisitorModel createVisitor(PostVisitorRequest postVisitorRequest) {
        String visitorId = Utils.generateVisitorId();
        VisitorModel visitorModel = visitorMapper.postVisitorRequestToVisitorModel(postVisitorRequest);
        visitorModel.setId(visitorId);
        visitorPersistenceService.addNewVisitorModel(visitorModel);
        return visitorModel;
    }

    public VisitorModel getVisitor(String visitorId) {
        return visitorPersistenceService.getVisitorModel(visitorId);
    }

    public List<VisitorModel> getVisitors(VisitorContactQueryParameters queries) {
        List<VisitorModel> retrievedVisitorModels = visitorPersistenceService.getVisitorModels()
            .stream()
            .filter(visitorModel -> queries.getFirstName().isBlank() || visitorModel.getFirstName()
                .contains(queries.getFirstName()))
            .filter(visitorModel -> queries.getLastName().isBlank() || visitorModel.getLastName()
                .contains(queries.getLastName()))
            .filter(visitorModel -> queries.getEmail().isBlank() || visitorModel.getEmail().contains(queries.getEmail()))
            .filter(visitorModel -> queries.getPhoneNumber().isBlank() || visitorModel.getPhoneNumber()
                .contains(queries.getPhoneNumber()))
            .collect(Collectors.toList());
        if (retrievedVisitorModels.isEmpty()) {
            throw new VisitorsNotFoundException();
        } else {
            return retrievedVisitorModels;
        }
    }

    public VisitorModel updateVisitor(String visitorId, PutVisitorRequest putVisitorRequest) {
        VisitorModel visitorModel = visitorMapper.putUserRequestToVisitorModel(putVisitorRequest);
        visitorModel.setId(visitorId);
        visitorPersistenceService.updateVisitorModel(visitorId, visitorModel);
        return visitorModel;
    }

    public void deleteVisitor(String visitorId) {
        visitorPersistenceService.deleteVisitorModel(visitorId);
    }
}
