package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeValue;
    private Double discountPercentage;
    
    // Field for Error 1 (getActive)
    private boolean active = true;

    // Relationship for Error 2 & 4 (getCampaign)
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    // Relationship for Error 3 (getInfluencer)
    @ManyToOne
    @JoinColumn(name = "influencer_id")
    private Influencer influencer;

    public DiscountCode() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeValue() { return codeValue; }
    public void setCodeValue(String codeValue) { this.codeValue = codeValue; }

    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) { this.discountPercentage = discountPercentage; }

    // Required by SaleTransactionServiceImpl
    public boolean isActive() { return active; } 
    public void setActive(boolean active) { this.active = active; }

    // Required by RoiServiceImpl
    public Campaign getCampaign() { return campaign; }
    public void setCampaign(Campaign campaign) { this.campaign = campaign; }

    public Influencer getInfluencer() { return influencer; }
    public void setInfluencer(Influencer influencer) { this.influencer = influencer; }
}