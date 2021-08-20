package com.example.userservice.mappers;

import com.example.userservice.UserContact;
import com.example.userservice.requests.PostUserRequest;
import com.example.userservice.requests.PutUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserContact postUserRequestToUserContact(PostUserRequest postUserRequest);

    @Mapping(target = "id", ignore = true)
    UserContact putUserRequestToUserContact(PutUserRequest putUserRequest);
}
