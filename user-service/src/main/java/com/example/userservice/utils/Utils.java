package com.example.userservice.utils;

import java.util.UUID;

public class Utils {

    public static String generateUserId() {
        return UUID.randomUUID().toString();
    }
}
