package com.example.userservice.utils;

import java.util.UUID;

public class Utils {

    public static String generateVisitorId() {
        return UUID.randomUUID().toString();
    }
}
