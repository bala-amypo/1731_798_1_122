package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleTransactionDTO {
    private Long id;
    private DiscountCodeDTO discountCode;
    private BigDecimal saleAmount;
    private LocalDateTime transactionDate;

    // Constructors
    public SaleTransactionDTO() {}

    public SaleTransactionDTO(Long id, DiscountCodeDTO discountCode, BigDecimal saleAmount, LocalDateTime transactionDate) {
        this.id = id;
        this.discountCode = discountCode;
        this.saleAmount = saleAmount;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiscountCodeDTO getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCodeDTO discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}