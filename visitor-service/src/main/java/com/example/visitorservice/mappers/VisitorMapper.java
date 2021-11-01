package com.example.visitorservice.mappers;

import com.example.visitorservice.models.VisitorModel;
import com.example.visitorservice.requests.PostVisitorRequest;
import com.example.visitorservice.requests.PutVisitorRequest;
import com.example.visitorservice.responces.GetVisitorResponse;
import com.example.visitorservice.responces.PostVisitorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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
