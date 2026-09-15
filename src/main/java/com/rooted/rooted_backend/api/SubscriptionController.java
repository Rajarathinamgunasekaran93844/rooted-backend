package com.rooted.rooted_backend.api;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rooted.rooted_backend.subscription.CreateSubscriptionRequest;
import com.rooted.rooted_backend.subscription.SubscriptionResponse;
import com.rooted.rooted_backend.subscription.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")

public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse create(@Valid @RequestBody CreateSubscriptionRequest request) {
        return subscriptionService.create(request);
    }
}
