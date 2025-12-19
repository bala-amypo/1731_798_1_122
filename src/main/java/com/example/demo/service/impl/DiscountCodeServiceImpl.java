package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Requirement 2.3: codeValue field
    private String codeValue;

    // Requirement 2.3: discountPercentage (0 <= value <= 100)
    private Double discountPercentage;

    private boolean active = true;

    // Relationship: Many discount codes can belong to one Influencer
    @ManyToOne
    @JoinColumn(name = "influencer_id")
    private Influencer influencer;

    // Relationship: Many discount codes can belong to one Campaign
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    // Relationship: One discount code can have many sales
    @OneToMany(mappedBy = "discountCode", cascade = CascadeType.ALL)
    private List<SaleTransaction> sales;

    // 1. No-argument constructor
    public DiscountCode() {}

    // 2. Parameterized constructor
    public DiscountCode(String codeValue, Double discountPercentage) {
        this.codeValue = codeValue;
        this.discountPercentage = discountPercentage;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeValue() { return codeValue; }
    public void setCodeValue(String codeValue) { this.codeValue = codeValue; }

    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) { this.discountPercentage = discountPercentage; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Influencer getInfluencer() { return influencer; }
    public void setInfluencer(Influencer influencer) { this.influencer = influencer; }

    public Campaign getCampaign() { return campaign; }
    public void setCampaign(Campaign campaign) { this.campaign = campaign; }

    public List<SaleTransaction> getSales() { return sales; }
    public void setSales(List<SaleTransaction> sales) { this.sales = sales; }
}