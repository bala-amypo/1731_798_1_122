package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;
    
    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "discountCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<SaleTransaction> saleTransactions = new java.util.ArrayList<>();

    // Constructors
    public DiscountCode() {}

    public DiscountCode(String code, Influencer influencer, Campaign campaign, Double discountPercentage, Boolean active) {
        this.code = code;
        this.influencer = influencer;
        this.campaign = campaign;
        this.discountPercentage = discountPercentage;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Influencer getInfluencer() {
        return influencer;
    }

    public void setInfluencer(Influencer influencer) {
        this.influencer = influencer;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public java.util.List<SaleTransaction> getSaleTransactions() {
        return saleTransactions;
    }

    public void setSaleTransactions(java.util.List<SaleTransaction> saleTransactions) {
        this.saleTransactions = saleTransactions;
    }
}