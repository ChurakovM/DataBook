package com.example.authenticationservice.mappers;

import com.example.authenticationservice.models.UserModel;
import com.example.authenticationservice.requests.PostUserRequest;
import com.example.authenticationservice.requests.PostUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "encryptedPassword", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserModel postUserRequestToUserModel(PostUserRequest postUserRequest);

    @Mapping(target = "userName", source = "userName") // TODO why does it not work properly?
    PostUserResponse postEndUserRequestToPostUserResponse(PostUserRequest postUserRequest);

//    @Mapping(target = "userName", source = "userName")
//    UserDetails userModelToUserDetails(UserModel userModel);
}
