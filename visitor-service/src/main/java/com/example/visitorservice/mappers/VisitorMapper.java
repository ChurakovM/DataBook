package com.example.visitorservice.mappers;

import com.example.visitorservice.models.VisitorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import visitorservice.requests.PostVisitorRequest;
import visitorservice.requests.PutVisitorRequest;
import visitorservice.responses.GetVisitorResponse;
import visitorservice.responses.PostVisitorResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface VisitorMapper {

    @Mapping(target = "id", ignore = true)
    VisitorModel postVisitorRequestToVisitorModel(PostVisitorRequest postVisitorRequest);

    @Mapping(target = "id", ignore = true)
    VisitorModel putUserRequestToVisitorModel(PutVisitorRequest putVisitorRequest);

    // Visitor Model to other objets
    PostVisitorResponse visitorModelToPostVisitorResponse(VisitorModel visitorModel);
    GetVisitorResponse visitorModelToGetVisitorResponse(VisitorModel visitorModel);
}
