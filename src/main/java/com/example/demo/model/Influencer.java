package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String socialHandle;
    private String email;
    private Boolean active = true;
    private Date createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}