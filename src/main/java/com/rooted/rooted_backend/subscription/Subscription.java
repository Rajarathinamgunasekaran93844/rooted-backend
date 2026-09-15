package com.rooted.rooted_backend.subscription;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(length = 40)
    private String interest;

    @Column(nullable = false, updatable = false)
    private Instant subscribedAt;

    protected Subscription() {
    }

    public Subscription(String name, String email, String interest) {
        this.name = name;
        this.email = email;
        this.interest = interest;
    }

    @PrePersist
    void setSubscribedAt() {
        subscribedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getInterest() {
        return interest;
    }

    public Instant getSubscribedAt() {
        return subscribedAt;
    }
}
