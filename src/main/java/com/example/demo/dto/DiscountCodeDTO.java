package com.example.demo.dto;

public class DiscountCodeDTO {
    private Long id;
    private String code;
    private InfluencerDTO influencer;
    private CampaignDTO campaign;
    private Double discountPercentage;
    private Boolean active;

    // Constructors
    public DiscountCodeDTO() {}

    public DiscountCodeDTO(Long id, String code, InfluencerDTO influencer, CampaignDTO campaign, 
                          Double discountPercentage, Boolean active) {
        this.id = id;
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

    public InfluencerDTO getInfluencer() {
        return influencer;
    }

    public void setInfluencer(InfluencerDTO influencer) {
        this.influencer = influencer;
    }

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignDTO campaign) {
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
}