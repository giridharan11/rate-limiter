package com.ratelimiter.ratelimiter.controller;

import com.ratelimiter.ratelimiter.annotation.RateLimit;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SampleApiController {

    @GetMapping("/default")
    public String defaultRateLimited() {
        return "This endpoint uses default rate limits.";
    }

    @RateLimit(limit = 5, windowInSeconds = 30)
    @GetMapping("/custom")
    public String customRateLimited() {
        return "This endpoint has a custom rate limit.";
    }
}