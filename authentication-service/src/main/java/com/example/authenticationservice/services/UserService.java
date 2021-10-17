package com.example.authenticationservice.services;

import com.example.authenticationservice.mappers.UserMapper;
import com.example.authenticationservice.models.UserModel;
import com.example.authenticationservice.persistence.UsersRepository;
import com.example.authenticationservice.requests.PostUserRequest;
import com.example.authenticationservice.requests.PostUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UsersRepository usersRepository;

    // TODO how to create a default admin once the service started?

    public PostUserResponse createUser(PostUserRequest postUserRequest) {
        PostUserResponse createdEndUser = userMapper.postEndUserRequestToPostUserResponse(postUserRequest);
        UserModel newUserForRepo = userMapper.postUserRequestToUserModel(postUserRequest);
        // TODO start to use hash passwords, learn difference between hash and encrypted passwords
        newUserForRepo.setEncryptedPassword("test"); // TODO add remove it
        usersRepository.save(newUserForRepo);
        return createdEndUser;
    }
}
