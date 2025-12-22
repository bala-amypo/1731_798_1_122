// Campaign.java
package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String campaignName;
    private LocalDate startDate;
    private LocalDate endDate;
    
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<DiscountCode> discountCodes = new ArrayList<>();
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCampaignName() { return campaignName; }
    public void setCampaignName(String campaignName) { this.campaignName = campaignName; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public List<DiscountCode> getDiscountCodes() { return discountCodes; }
    public void setDiscountCodes(List<DiscountCode> discountCodes) { this.discountCodes = discountCodes; }
}