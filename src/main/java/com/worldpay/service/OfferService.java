package com.worldpay.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldpay.dao.OfferRepository;
import com.worldpay.model.Offer;
import com.worldpay.model.Offer.Status;


@Service
public class OfferService {
    
    private final Logger logger = LoggerFactory.getLogger(OfferService.class);
    
    @Autowired
    private OfferRepository offerRepository;

    public Offer createOffer(Offer offer) {
        logger.info("Creating offer: " + offer.toString());
        return offerRepository.save(offer);
    }
    
    public Offer getOfferById(Long id) {
        logger.info("Finding offer by ID: " + id);
        return offerRepository.findOne(id);
    }
    
    public List<Offer> getOffersByDescriptionContaining(String description) {
        logger.info("Finding offer by description: " + description);
        return offerRepository.findByDescriptionContaining(description);
    }
    
    public List<Offer> getAllOffers() {
        logger.info("Finding all offers");
        return StreamSupport.stream(offerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());                
    }
    
    public void expireOffers() {
        logger.info("Expiring offers ...");
        //get offers ready to expire                
        LocalDate today = LocalDate.now();
        List<Offer> offers = offerRepository.findByStatusAndExpiration(Status.ACTIVE, today);
        
        //set status EXPIRED to selected offers
        offers.stream().forEach(o -> o.setStatus(Status.EXPIRED));
        
        //persist changes
        offerRepository.save(offers);
        logger.info("Done expiring offers");
    }
}
