// // Influencer.java
// package com.example.demo.model;

// import javax.persistence.*;
// import java.util.ArrayList;
// import java.util.List;

// @Entity
// @Table(name = "influencers")
// public class Influencer {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     private String name;
//     private String socialHandle;
//     private boolean active = true;
    
//     @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
//     private List<DiscountCode> discountCodes = new ArrayList<>();
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
    
//     public String getSocialHandle() { return socialHandle; }
//     public void setSocialHandle(String socialHandle) { this.socialHandle = socialHandle; }
    
//     public boolean isActive() { return active; }
//     public void setActive(boolean active) { this.active = active; }
    
//     public List<DiscountCode> getDiscountCodes() { return discountCodes; }
//     public void setDiscountCodes(List<DiscountCode> discountCodes) { this.discountCodes = discountCodes; }
// }

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    
    @Column(unique = true)
    private String socialHandle;
    
    private Boolean active = true;  // Use Boolean (wrapper class), not boolean (primitive)
    
    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DiscountCode> discountCodes = new ArrayList<>();
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSocialHandle() {
        return socialHandle;
    }
    
    public void setSocialHandle(String socialHandle) {
        this.socialHandle = socialHandle;
    }
    
    // Use getActive() method instead of isActive() to avoid primitive boolean issues
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    // Optional: Keep isActive() but make sure it returns Boolean, not boolean
    public Boolean isActive() {
        return active;
    }
    
    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }
    
    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }
}