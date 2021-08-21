package com.example.userservice;

import com.example.userservice.models.UserContact;
import com.example.userservice.models.UserContactQueryParameters;
import com.example.userservice.requests.PostUserRequest;
import com.example.userservice.requests.PutUserRequest;
import com.example.userservice.services.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("users")// http://localhost:8080/users
public class UserController {

    // TODO read about reflection
    private final UserService userService;

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
        return new ResponseEntity<>(userContact, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}",
        produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        })
    public ResponseEntity<UserContact> getUser(@PathVariable String userId) {
        UserContact userContact = userService.getUser(userId);
        return new ResponseEntity<>(userContact, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(
        @RequestParam(value = "firstName", defaultValue = "", required = false) String firstName,
        @RequestParam(value = "lastName", defaultValue = "", required = false) String lastName,
        @RequestParam(value = "email", defaultValue = "", required = false) String email,
        @RequestParam(value = "phoneNumber", defaultValue = "", required = false) String phoneNumber) {
        UserContactQueryParameters queries = new UserContactQueryParameters(firstName, lastName, email, phoneNumber);
        List<UserContact> userContacts = userService.getUsers(queries);
        return new ResponseEntity<>(userContacts, HttpStatus.OK);
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
        UserContact userContact = userService.updateUser(userId, putUserRequest);
        return new ResponseEntity<>(userContact, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
