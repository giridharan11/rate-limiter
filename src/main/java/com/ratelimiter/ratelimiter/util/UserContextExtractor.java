package com.ratelimiter.ratelimiter.util;

import jakarta.servlet.http.HttpServletRequest;

public class UserContextExtractor {
    public static String extractUserId(HttpServletRequest request) {
        String userId = request.getHeader("X-User-Id");
        return (userId != null) ? userId : "anonymous";
    }
}