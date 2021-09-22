package com.example.authenticationservice.mappers;

import com.example.authenticationservice.models.EndUserModel;
import com.example.authenticationservice.requests.PostEndUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface EndUserMapper {

    @Mapping(target = "encryptedPassword", ignore = true)
    @Mapping(target = "id", ignore = true)
    EndUserModel postEndUserRequestToEndUserModel(PostEndUserRequest postEndUserRequest);
}
