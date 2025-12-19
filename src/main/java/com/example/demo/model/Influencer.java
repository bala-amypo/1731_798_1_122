package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "influencers")
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String socialHandle;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSocialHandle() { return socialHandle; }
    public void setSocialHandle(String socialHandle) { this.socialHandle = socialHandle; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean isActive() { return active; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}