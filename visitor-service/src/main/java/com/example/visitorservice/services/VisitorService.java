package com.example.visitorservice.services;

import com.example.visitorservice.exceptions.VisitorsNotFoundException;
import com.example.visitorservice.mappers.VisitorMapper;
import com.example.visitorservice.models.VisitorContactQueryParameters;
import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.persistence.VisitorPersistenceService;
import com.example.visitorservice.requests.PostVisitorRequest;
import com.example.visitorservice.requests.PutVisitorRequest;
import com.example.visitorservice.utils.Utils;
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
