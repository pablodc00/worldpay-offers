package com.worldpay.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worldpay.model.Offer;
import com.worldpay.service.OfferService;
import com.worldpay.util.OfferConstants;

@RestController
@RequestMapping(OfferConstants.API_URI)
public class OfferController {
    
    private final Logger logger = LoggerFactory.getLogger(OfferController.class);
    
    @Autowired
    private OfferService offerService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/{offerId}")    
    public ResponseEntity<Offer> getOffer(@PathVariable Long offerId) {
        Offer result = offerService.getOfferById(offerId);
        if (null == result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/byDescription")    
    public ResponseEntity<List<Offer>> getOffer(@RequestParam(value="description") String description) {
        List<Offer> offers = offerService.getOffersByDescriptionContaining(description);
        return ResponseEntity.ok(offers);
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Offer>> getOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        
        if (offer.getIssued().isAfter(offer.getExpiration())) {
            logger.error("Issue Date can't be after than Expiration Date");                        
            return ResponseEntity.badRequest().build();           
        }        
        
        Offer result =  offerService.createOffer(offer);
        
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(OfferConstants.API_URI+"/{offerId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
