package com.worldpay.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Currency;
import java.util.Locale;

import com.worldpay.model.Offer;
import com.worldpay.model.Offer.Status;

public class OfferUtil {

    public static Offer getMockOffer() {
        
        Locale defaultLocale = new Locale("en", "US", "USD");
        LocalDate issued = LocalDate.of(2018, Month.APRIL, 01);
        LocalDate expiration = LocalDate.now(); 
        
        Offer offer = new Offer();
        offer.setCurrency(Currency.getInstance(defaultLocale));
        offer.setIssued(issued);
        offer.setExpiration(expiration);
        offer.setStatus(Status.ACTIVE);
        offer.setPrice(new BigDecimal("123.12"));
        return offer;
    }    
    
}


