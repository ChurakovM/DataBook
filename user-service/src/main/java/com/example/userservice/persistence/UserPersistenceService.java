package com.example.userservice.persistence;

import com.example.userservice.UserContact;
import com.example.userservice.exceptions.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceService {

    // TODO replace this implementation in the future with a real DB
    // TODO read about Concurrent Hash Map
    private Map<String, UserContact> users = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger("");

    public void addNewUserContact(UserContact userContact) {
        users.put(userContact.getId(), userContact);
    }

    public UserContact getUserContact(String userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            LOGGER.debug("User id = '" + userId + "' was not found in the DB");
            throw new UserNotFoundException();
        }
    }

    public void updateUserContact(String userId, UserContact userContact) {
        if (users.containsKey(userId)) {
            users.replace(userId, userContact);
        } else {
            LOGGER.debug("User id = '" + userId + "' was not found in the DB");
            throw new UserNotFoundException();
        }
    }

    public void deleteUserContact(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
        } else {
            LOGGER.debug("User id = '" + userId + "' was not found in the DB");
            throw new UserNotFoundException();
        }
    }
}
