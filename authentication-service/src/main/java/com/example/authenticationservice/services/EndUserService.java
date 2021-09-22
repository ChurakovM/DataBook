package com.example.authenticationservice.services;

import com.example.authenticationservice.mappers.EndUserMapper;
import com.example.authenticationservice.models.EndUserModel;
import com.example.authenticationservice.persistence.EndUserPersistenceService;
import com.example.authenticationservice.persistence.EndUsersRepository;
import com.example.authenticationservice.requests.PostEndUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EndUserService {

    private final EndUserPersistenceService endUserPersistenceService;
    private final EndUserMapper endUserMapper;
    //private final EndUsersRepository endUsersRepository;

    public EndUserModel createEndUser(PostEndUserRequest postEndUserRequest) {
        EndUserModel createdEndUser = endUserMapper.postEndUserRequestToEndUserModel(postEndUserRequest);
        //endUserPersistenceService.addEndUser(createdEndUser);
        createdEndUser.setEncryptedPassword("test"); // TODO add remove it
        endUserPersistenceService.save(createdEndUser);
        return createdEndUser;
    }
}
