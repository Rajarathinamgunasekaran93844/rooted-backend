package com.rooted.rooted_backend.subscription;

public class DuplicateSubscriptionException extends RuntimeException {

    public DuplicateSubscriptionException() {
        super("This email address is already subscribed.");
    }
}
