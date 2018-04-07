package com.worldpay.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.worldpay.model.Offer;
import com.worldpay.model.Offer.Status;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> findByDescriptionContaining(String description);
    
    List<Offer> findByStatusAndExpiration(Status status, LocalDate expiration);
}
