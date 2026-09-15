package com.rooted.rooted_backend.subscription;

import java.time.Instant;

public record SubscriptionResponse(Long id, String message, Instant subscribedAt) {
}
