package com.example.visitorservice.services;

import static com.example.visitorservice.persistence.SpecificationUtils.containsEmail;
import static com.example.visitorservice.persistence.SpecificationUtils.containsFirstName;
import static com.example.visitorservice.persistence.SpecificationUtils.containsLastName;
import static com.example.visitorservice.persistence.SpecificationUtils.containsPhoneNumber;
import static com.example.visitorservice.persistence.SpecificationUtils.matchesId;
import static org.springframework.data.jpa.domain.Specification.where;

import com.example.visitorservice.exceptions.VisitorNotFoundException;
import com.example.visitorservice.mappers.VisitorMapper;
import com.example.visitorservice.models.VisitorContactQueryParameters;
import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.persistence.VisitorsRepository;
import com.example.visitorservice.requests.PostVisitorRequest;
import com.example.visitorservice.requests.PutVisitorRequest;
import com.example.visitorservice.responces.GetVisitorResponse;
import com.example.visitorservice.responces.GetVisitorsResponse;
import com.example.visitorservice.responces.PostVisitorResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorService {

    private final VisitorsRepository visitorsRepository;
    private final VisitorMapper visitorMapper;

    public PostVisitorResponse createVisitor(PostVisitorRequest postVisitorRequest) {
        VisitorModel visitorModel = visitorMapper.postVisitorRequestToVisitorModel(postVisitorRequest);
        visitorsRepository.save(visitorModel);
        return visitorMapper.visitorModelToPostVisitorResponse(visitorModel);
    }

    public GetVisitorResponse getVisitor(String visitorId) {
        VisitorModel visitorModel = findVisitorInRepository(visitorId);
        return visitorMapper.visitorModelToGetVisitorResponse(visitorModel);
    }

    public GetVisitorsResponse getVisitors(VisitorContactQueryParameters queries) {
        List<VisitorModel> filteredVisitors = visitorsRepository.findAll(
            where(containsFirstName(queries.getFirstName()))
                .and(containsLastName(queries.getLastName()))
                .and(containsEmail(queries.getEmail()))
                .and(containsPhoneNumber(queries.getPhoneNumber())));
        GetVisitorsResponse finalResponse = new GetVisitorsResponse();
        finalResponse.setListOfVisitors(filteredVisitors);
        return finalResponse;
    }

    public VisitorModel updateVisitor(String visitorId, PutVisitorRequest putVisitorRequest) {
        VisitorModel foundModel = findVisitorInRepository(visitorId);
        foundModel.setFirstName(putVisitorRequest.getFirstName());
        foundModel.setLastName(putVisitorRequest.getLastName());
        foundModel.setEmail(putVisitorRequest.getEmail());
        foundModel.setPhoneNumber(putVisitorRequest.getPhoneNumber());
        visitorsRepository.save(foundModel);
        return foundModel;
    }

    public void deleteVisitor(String visitorId) {
        VisitorModel foundModel = findVisitorInRepository(visitorId);
        visitorsRepository.delete(foundModel);
    }

    private VisitorModel findVisitorInRepository(String visitorId) {
        return visitorsRepository.findOne(where(matchesId(visitorId)))
            .orElseThrow(() -> new VisitorNotFoundException(visitorId));
    }
}