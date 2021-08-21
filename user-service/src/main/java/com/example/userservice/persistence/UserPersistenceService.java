package com.example.userservice.persistence;

import com.example.userservice.models.UserContact;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.exceptions.UsersNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserPersistenceService {

    // TODO replace this implementation in the future with a real DB
    // TODO read about Concurrent Hash Map
    private final Map<String, UserContact> users = new HashMap<>();

    public void addNewUserContact(UserContact userContact) {
        users.put(userContact.getId(), userContact);
    }

    public UserContact getUserContact(String userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            log.debug("User id = '{}' was not found in the DB", userId);
            throw new UserNotFoundException(userId);
        }
    }

    public List<UserContact> getUserContacts() {
        if (users.isEmpty()) {
            log.debug("No users were found in the DB");
            throw new UsersNotFoundException();
        } else {
            return new ArrayList<>(users.values());
        }
    }

    public void updateUserContact(String userId, UserContact userContact) {
        if (users.containsKey(userId)) {
            users.replace(userId, userContact);
        } else {
            log.debug("User id = '{}' was not found in the DB", userId);
            throw new UserNotFoundException(userId);
        }
    }

    public void deleteUserContact(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
        } else {
            log.debug("User id = '{}' was not found in the DB", userId);
            throw new UserNotFoundException(userId);
        }
    }
}
