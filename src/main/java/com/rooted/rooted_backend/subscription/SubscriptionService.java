package com.rooted.rooted_backend.subscription;

import java.util.Locale;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public SubscriptionResponse create(CreateSubscriptionRequest request) {
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        if (subscriptionRepository.existsByEmail(email)) {
            throw new DuplicateSubscriptionException();
        }

        try {
            Subscription subscription = subscriptionRepository.saveAndFlush(
                    new Subscription(request.name().trim(), email, normalizeInterest(request.interest()))
            );
            return new SubscriptionResponse(
                    subscription.getId(), "Subscription created.", subscription.getSubscribedAt()
            );
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateSubscriptionException();
        }
    }

    private String normalizeInterest(String interest) {
        return interest == null || interest.isBlank() ? null : interest.trim();
    }
}
