package com.example.userservice;

import com.example.userservice.persistence.UserPersistenceService;
import com.example.userservice.requests.PostUserRequest;
import com.example.userservice.requests.PutUserRequest;
import com.example.userservice.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPersistenceService userPersistenceService;

    // TODO test this request with XML
    @PostMapping(
        consumes = { // TODO should they be specified by default?
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        },
        produces = { // TODO should they be specified by default?
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ResponseEntity<UserContact> createUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        UserContact userContact = userService.createUser(postUserRequest);
        userPersistenceService.addNewUserContact(userContact);
        return new ResponseEntity<>(userContact, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}",
        produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        })
    public ResponseEntity<UserContact> getUser(@PathVariable String userId) {
        UserContact userContact = userPersistenceService.getUserContact(userId);
        return new ResponseEntity<>(userContact, HttpStatus.OK);
    }

    @GetMapping // method with query parameters
    public String getUsers(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
        @RequestParam(value = "limit", defaultValue = "20", required = false) int limit) {
        return "'getUsers' method was called with page = " + page + " and limit = " + limit;
    }

    @PutMapping(path = "/{userId}",
        consumes = { // TODO should they be specified by default?
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        },
        produces = { // TODO should they be specified by default?
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ResponseEntity<UserContact> updateUser(@PathVariable String userId,
        @Valid @RequestBody PutUserRequest putUserRequest) {
        UserContact initialUserContact = userPersistenceService.getUserContact(userId);
        UserContact updatedUserContact = userService.updateUser(initialUserContact, putUserRequest);
        userPersistenceService.updateUserContact(userId, updatedUserContact);
        return new ResponseEntity<>(updatedUserContact, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        userPersistenceService.deleteUserContact(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
