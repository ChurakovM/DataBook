package com.example.visitorservice.utils;

import java.util.UUID;

public class Utils {

    public static String generateVisitorId() {
        return UUID.randomUUID().toString();
    }
}
