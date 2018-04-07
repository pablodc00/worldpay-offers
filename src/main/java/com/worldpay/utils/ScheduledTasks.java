package com.worldpay.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.worldpay.service.OfferService;


@Component
public class ScheduledTasks {

    @Autowired
    private OfferService offerService;
    
    @Scheduled(cron = "0 15 1 ? * *")
    public void checkCurrenyExchange() {
        offerService.expireOffers();
    }    
    
}
