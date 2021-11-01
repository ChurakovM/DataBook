package com.example.visitorservice.services;

import com.example.visitorservice.exceptions.VisitorsNotFoundException;
import com.example.visitorservice.mappers.VisitorMapper;
import com.example.visitorservice.models.VisitorContactQueryParameters;
import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.persistence.VisitorPersistenceService;
import com.example.visitorservice.persistence.VisitorsRepository;
import com.example.visitorservice.requests.PostVisitorRequest;
import com.example.visitorservice.requests.PutVisitorRequest;
import com.example.visitorservice.responces.GetVisitorResponse;
import com.example.visitorservice.responces.GetVisitorsResponse;
import com.example.visitorservice.responces.PostVisitorResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorService {

    private final VisitorPersistenceService visitorPersistenceService;
    private final VisitorsRepository visitorsRepository;
    private final VisitorMapper visitorMapper;

    public PostVisitorResponse createVisitor(PostVisitorRequest postVisitorRequest) {
        VisitorModel visitorModel = visitorMapper.postVisitorRequestToVisitorModel(postVisitorRequest);
        visitorsRepository.save(visitorModel);
        return visitorMapper.visitorModelToPostVisitorResponse(visitorModel);
    }

    public GetVisitorResponse getVisitor(Long visitorId) {
        VisitorModel visitorModel = visitorsRepository.findById(visitorId).orElseThrow(VisitorsNotFoundException::new);
        return visitorMapper.visitorModelToGetVisitorResponse(visitorModel);
    }

    public GetVisitorsResponse getVisitors(VisitorContactQueryParameters queries) {
        // TODO filters don't work
        List<VisitorModel> filteredVisitors = StreamSupport.stream(visitorsRepository.findAll().spliterator(), false)
            .filter(visitorModel -> visitorModel.getFirstName().contains(queries.getFirstName()))
            .filter(visitorModel -> visitorModel.getLastName().contains(queries.getLastName()))
            .filter(visitorModel -> visitorModel.getEmail().contains(queries.getEmail()))
            .filter(visitorModel -> visitorModel.getPhoneNumber().contains(queries.getPhoneNumber()))
            .collect(Collectors.toList());
        List<GetVisitorResponse> foundVisitors = new ArrayList<>();
        filteredVisitors.forEach(visitorModel -> {
            GetVisitorResponse foundVisitor = visitorMapper.visitorModelToGetVisitorResponse(visitorModel);
            foundVisitors.add(foundVisitor);
        });
        GetVisitorsResponse finalResponse = new GetVisitorsResponse();
        finalResponse.setListOfVisitors(foundVisitors);
        return finalResponse;
    }

    // TODO implement this functionality
//    public VisitorModel updateVisitor(Long visitorId, PutVisitorRequest putVisitorRequest) {
//        VisitorModel visitorModel = visitorMapper.putUserRequestToVisitorModel(putVisitorRequest);
//        visitorModel.setId(visitorId);
//        visitorPersistenceService.updateVisitorModel(visitorId, visitorModel);
//    }

    public void deleteVisitor(Long visitorId) {
        visitorsRepository.deleteById(visitorId);
    }
}
