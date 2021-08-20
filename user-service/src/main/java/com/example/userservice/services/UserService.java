package com.example.userservice.services;

import com.example.userservice.UserContact;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.persistence.UserPersistenceService;
import com.example.userservice.requests.PostUserRequest;
import com.example.userservice.requests.PutUserRequest;
import com.example.userservice.utils.Utils;
import java.util.List;
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

    public List<UserContact> getUsers() {
        return userPersistenceService.getUserContacts();
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
