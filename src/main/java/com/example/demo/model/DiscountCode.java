package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "code_value", nullable = false, unique = true)
    private String codeValue;
    
    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;
    
    @OneToMany(mappedBy = "discountCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleTransaction> saleTransactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "discountCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoiReport> roiReports = new ArrayList<>();

    // Constructors
    public DiscountCode() {}

    public DiscountCode(String codeValue, Double discountPercentage) {
        this.codeValue = codeValue;
        this.discountPercentage = discountPercentage;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    public List<SaleTransaction> getSaleTransactions() {
        return saleTransactions;
    }

    public void setSaleTransactions(List<SaleTransaction> saleTransactions) {
        this.saleTransactions = saleTransactions;
    }

    public List<RoiReport> getRoiReports() {
        return roiReports;
    }

    public void setRoiReports(List<RoiReport> roiReports) {
        this.roiReports = roiReports;
    }
}