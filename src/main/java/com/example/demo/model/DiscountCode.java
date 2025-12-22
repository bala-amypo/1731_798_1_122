// DiscountCode.java
package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String codeValue;
    private Double discountPercentage;
    
    @ManyToOne
    @JoinColumn(name = "influencer_id")
    private Influencer influencer;
    
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
    
    @OneToMany(mappedBy = "discountCode", cascade = CascadeType.ALL)
    private List<SaleTransaction> sales = new ArrayList<>();
    
    @OneToOne(mappedBy = "discountCode", cascade = CascadeType.ALL)
    private RoiReport roiReport;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCodeValue() { return codeValue; }
    public void setCodeValue(String codeValue) { this.codeValue = codeValue; }
    
    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) { this.discountPercentage = discountPercentage; }
    
    public Influencer getInfluencer() { return influencer; }
    public void setInfluencer(Influencer influencer) { this.influencer = influencer; }
    
    public Campaign getCampaign() { return campaign; }
    public void setCampaign(Campaign campaign) { this.campaign = campaign; }
    
    public List<SaleTransaction> getSales() { return sales; }
    public void setSales(List<SaleTransaction> sales) { this.sales = sales; }
    
    public RoiReport getRoiReport() { return roiReport; }
    public void setRoiReport(RoiReport roiReport) { this.roiReport = roiReport; }
}