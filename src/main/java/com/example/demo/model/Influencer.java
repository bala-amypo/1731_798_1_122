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
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "influencers")
@Schema(description = "Influencer entity representing a social media influencer")
public class Influencer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Influencer ID", example = "1")
    private Long id;
    
    @Schema(description = "Influencer name", example = "John Doe", required = true)
    private String name;
    
    @Column(unique = true)
    @Schema(description = "Social media handle", example = "@johndoe", required = true)
    private String socialHandle;
    
    @Schema(description = "Whether the influencer is active", example = "true")
    private Boolean active = true;
    
    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Schema(description = "Discount codes associated with this influencer")
    private List<DiscountCode> discountCodes = new ArrayList<>();
    
    // Constructors
    public Influencer() {}
    
    public Influencer(String name, String socialHandle) {
        this.name = name;
        this.socialHandle = socialHandle;
    }
    
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
    
    public Boolean isActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }
    
    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }
}