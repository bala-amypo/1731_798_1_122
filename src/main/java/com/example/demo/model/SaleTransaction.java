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

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    private BigDecimal transactionAmount; // Must be this name
    private Timestamp transactionDate;
    private Long customerId;

    public SaleTransaction() {}
    public BigDecimal getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(BigDecimal transactionAmount) { this.transactionAmount = transactionAmount; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
    public DiscountCode getDiscountCode() { return discountCode; }
    public void setDiscountCode(DiscountCode discountCode) { this.discountCode = discountCode; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}