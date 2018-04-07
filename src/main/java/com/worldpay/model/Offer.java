package com.worldpay.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.worldpay.utils.LocalDateDeserializer;
import com.worldpay.utils.LocalDateSerializer;

@Entity
public class Offer {
    
    public enum Status {
        ACTIVE, EXPIRED, CANCELLED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Long id;
    private String description;
    private BigDecimal price;
    private Status status;
    private Currency currency;
    
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate issued;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)    
    private LocalDate expiration;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public LocalDate getIssued() {
        return issued;
    }
    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }
    public LocalDate getExpiration() {
        return expiration;
    }
    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return 
            new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", this.id)
                .append("description", this.description)
                .append("price", this.price)
                .append("currency", this.currency)
                .append("issued", this.issued)
                .append("expiration", this.expiration)
                .append("status", this.status)
                .toString();
        }    
    
}
