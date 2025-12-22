// RoiReport.java
package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "roi_reports")
public class RoiReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal totalSales = BigDecimal.ZERO;
    private Integer totalTransactions = 0;
    private Double roiPercentage = 0.0;
    
    @OneToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public BigDecimal getTotalSales() { return totalSales; }
    public void setTotalSales(BigDecimal totalSales) { 
        if (totalSales.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total sales cannot be negative");
        }
        this.totalSales = totalSales; 
    }
    
    public Integer getTotalTransactions() { return totalTransactions; }
    public void setTotalTransactions(Integer totalTransactions) { 
        if (totalTransactions < 0) {
            throw new IllegalArgumentException("Total transactions cannot be negative");
        }
        this.totalTransactions = totalTransactions; 
    }
    
    public Double getRoiPercentage() { return roiPercentage; }
    public void setRoiPercentage(Double roiPercentage) { this.roiPercentage = roiPercentage; }
    
    public DiscountCode getDiscountCode() { return discountCode; }
    public void setDiscountCode(DiscountCode discountCode) { this.discountCode = discountCode; }
}