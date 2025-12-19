package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "sale_transactions")
public class SaleTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Requirement 2.4: Many-to-one with DiscountCode
    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    // Requirement 2.4: transactionAmount (Must be BigDecimal)
    private BigDecimal transactionAmount;

    // Requirement 2.4: transactionDate (Timestamp or LocalDateTime)
    private Timestamp transactionDate;

    // Requirement 2.4: customerId (Identifier for mapping tests)
    private Long customerId;

    // 1. No-argument constructor
    public SaleTransaction() {}

    // 2. Parameterized constructor
    public SaleTransaction(DiscountCode discountCode, BigDecimal transactionAmount, Timestamp transactionDate, Long customerId) {
        this.discountCode = discountCode;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.customerId = customerId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DiscountCode getDiscountCode() { return discountCode; }
    public void setDiscountCode(DiscountCode discountCode) { this.discountCode = discountCode; }

    public BigDecimal getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(BigDecimal transactionAmount) { this.transactionAmount = transactionAmount; }

    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}