package com.example.userservice.services;

import com.example.userservice.models.UserContact;
import com.example.userservice.exceptions.UsersNotFoundException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.models.UserContactQueryParameters;
import com.example.userservice.persistence.UserPersistenceService;
import com.example.userservice.requests.PostUserRequest;
import com.example.userservice.requests.PutUserRequest;
import com.example.userservice.utils.Utils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserPersistenceService userPersistenceService;
    private final UserMapper userMapper;

    public UserContact createUser(PostUserRequest postUserRequest) {
        String userId = Utils.generateUserId();
        UserContact userContact = userMapper.postUserRequestToUserContact(postUserRequest);
        userContact.setId(userId);
        userPersistenceService.addNewUserContact(userContact);
        return userContact;
    }

    public UserContact getUser(String userId) {
        return userPersistenceService.getUserContact(userId);
    }

    public List<UserContact> getUsers(UserContactQueryParameters queries) {
        List<UserContact> retrievedUserContacts = userPersistenceService.getUserContacts()
            .stream()
            .filter(userContact -> queries.getFirstName().isBlank() || userContact.getFirstName()
                .contains(queries.getFirstName()))
            .filter(userContact -> queries.getLastName().isBlank() || userContact.getLastName()
                .contains(queries.getLastName()))
            .filter(userContact -> queries.getEmail().isBlank() || userContact.getEmail().contains(queries.getEmail()))
            .filter(userContact -> queries.getPhoneNumber().isBlank() || userContact.getPhoneNumber()
                .contains(queries.getPhoneNumber()))
            .collect(Collectors.toList());
        if (retrievedUserContacts.isEmpty()) {
            throw new UsersNotFoundException();
        } else {
            return retrievedUserContacts;
        }
    }

    public UserContact updateUser(String userId, PutUserRequest putUserRequest) {
        UserContact userContact = userMapper.putUserRequestToUserContact(putUserRequest);
        userContact.setId(userId);
        userPersistenceService.updateUserContact(userId, userContact);
        return userContact;
    }

    public void deleteUser(String userId) {
        userPersistenceService.deleteUserContact(userId);
    }
}
