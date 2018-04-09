package com.worldpay.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.worldpay.model.Offer;
import com.worldpay.service.OfferService;
import com.worldpay.utils.OfferTestsUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OfferControllerTest {

    @InjectMocks
    private OfferController offerController = new OfferController();
    
    @Mock
    private OfferService offerServiceMock;
    
    @Test
    public void testCreateOffer() {
        Offer offer1 = OfferTestsUtil.getMockOffer();
        Offer offer2 = OfferTestsUtil.getMockOffer();
        offer2.setId(10L);
        
        when(offerServiceMock.createOffer(offer1)).thenReturn(offer2);
        final ResponseEntity<?> post = offerController.createOffer(offer1);
        assertEquals(post.getStatusCode(), HttpStatus.CREATED);
        
    }
    
}
