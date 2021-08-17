package com.example.userservice.service;

import com.example.userservice.UserContact;
import com.example.userservice.requests.PostUserRequest;
import com.example.userservice.requests.PutUserRequest;
import com.example.userservice.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserContact createUser(PostUserRequest postUserRequest) {
        String userId = Utils.generateUserId();
        UserContact userContact = new UserContact();
        userContact.setFirstName(postUserRequest.getFirstName());
        userContact.setLastName(postUserRequest.getLastName());
        userContact.setEmail(postUserRequest.getEmail());
        userContact.setPhoneNumber(postUserRequest.getPhoneNumber());
        userContact.setId(userId);
        return userContact;
    }

    public UserContact updateUser(UserContact userContact, PutUserRequest putUserRequest) {
        userContact.setFirstName(putUserRequest.getFirstName());
        userContact.setLastName(putUserRequest.getLastName());
        userContact.setEmail(putUserRequest.getEmail());
        userContact.setPhoneNumber(putUserRequest.getPhoneNumber());
        return userContact;
    }
}
