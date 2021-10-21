package com.example.userservice.mappers;

import com.example.userservice.models.VisitorModel;
import com.example.userservice.requests.PostVisitorRequest;
import com.example.userservice.requests.PutVisitorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface VisitorMapper {

    @Mapping(target = "id", ignore = true)
    VisitorModel postVisitorRequestToVisitorModel(PostVisitorRequest postVisitorRequest);

    @Mapping(target = "id", ignore = true)
    VisitorModel putUserRequestToVisitorModel(PutVisitorRequest putVisitorRequest);
}
