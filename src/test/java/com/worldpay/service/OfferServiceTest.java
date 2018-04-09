package com.worldpay.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.worldpay.dao.OfferRepository;
import com.worldpay.model.Offer;
import com.worldpay.model.Offer.Status;
import com.worldpay.utils.OfferTestsUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OfferServiceTest {

    

    
    @InjectMocks
    private OfferService offerService = new OfferService();
    
    @Mock
    private OfferRepository offerRepositoryMock;
    

    @Test
    public void testCreateOffer() {
        
        Offer offer1 = OfferTestsUtil.getMockOffer();
        
        Offer offer2 = OfferTestsUtil.getMockOffer();
        offer2.setId(10L);
        
        when(offerRepositoryMock.save(offer1)).thenReturn(offer2);
        Offer offer3 = offerService.createOffer(offer1);
        assertNotNull(offer3.getId());
        assertEquals(offer3.getId(), offer2.getId());
        
    }
    
    
    @Test
    public void testExpireOffers() {
        LocalDate today = LocalDate.now();
        List<Offer> offers = Lists.newArrayList();

        Offer offer = OfferTestsUtil.getMockOffer();
        offer.setId(10L);
        offers.add(offer);
        
        offer = OfferTestsUtil.getMockOffer();
        offer .setId(20L);
        offer.setDescription("Offer Two");
        offer.setPrice(new BigDecimal("542.63"));
        offers.add(offer);
        
        offer = OfferTestsUtil.getMockOffer();
        offer.setId(30L);
        offer.setDescription("Offer Three");
        offer.setPrice(new BigDecimal("953.54"));
        offers.add(offer);
        
        when(offerRepositoryMock.findByStatusAndExpiration(Status.ACTIVE, today)).thenReturn(offers);
        offerService.expireOffers();

        assertEquals(offers.get(0).getStatus(), Status.EXPIRED);
    }
    

}
