// Influencer.java
package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "influencers")
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String socialHandle;
    private boolean active = true;
    
    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private List<DiscountCode> discountCodes = new ArrayList<>();
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSocialHandle() { return socialHandle; }
    public void setSocialHandle(String socialHandle) { this.socialHandle = socialHandle; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public List<DiscountCode> getDiscountCodes() { return discountCodes; }
    public void setDiscountCodes(List<DiscountCode> discountCodes) { this.discountCodes = discountCodes; }
}