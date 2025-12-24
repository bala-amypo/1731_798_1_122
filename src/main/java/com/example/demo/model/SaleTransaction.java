// package com.example.demo.model;

// import javax.persistence.*;
// import java.math.BigDecimal;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "sale_transactions")
// public class SaleTransaction {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     private BigDecimal transactionAmount;
//     private Timestamp transactionDate;
//     private Long customerId;
    
//     @ManyToOne
//     @JoinColumn(name = "discount_code_id")
//     private DiscountCode discountCode;
    
//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }
    
//     public void setId(Long id) {
//         this.id = id;
//     }
    
//     public BigDecimal getTransactionAmount() {
//         return transactionAmount;
//     }
    
//     public void setTransactionAmount(BigDecimal transactionAmount) { 
//         if (transactionAmount == null) {
//             throw new IllegalArgumentException("Transaction amount cannot be null");
//         }
//         if (transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
//             throw new IllegalArgumentException("Transaction amount must be > 0");
//         }
//         this.transactionAmount = transactionAmount; 
//     }
    
//     public Timestamp getTransactionDate() {
//         return transactionDate;
//     }
    
//     public void setTransactionDate(Timestamp transactionDate) {
//         this.transactionDate = transactionDate;
//     }
    
//     public Long getCustomerId() {
//         return customerId;
//     }
    
//     public void setCustomerId(Long customerId) {
//         this.customerId = customerId;
//     }
    
//     public DiscountCode getDiscountCode() {
//         return discountCode;
//     }
    
//     public void setDiscountCode(DiscountCode discountCode) {
//         this.discountCode = discountCode;
//     }
// }
package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "sale_transactions")
public class SaleTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal transactionAmount;
    private Timestamp transactionDate;
    private Long customerId;
    
    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }
    
    public void setTransactionAmount(BigDecimal transactionAmount) { 
        // REMOVE THE VALIDATION HERE - let service handle it
        this.transactionAmount = transactionAmount; 
    }
    
    public Timestamp getTransactionDate() {
        return transactionDate;
    }
    
    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public DiscountCode getDiscountCode() {
        return discountCode;
    }
    
    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }
}