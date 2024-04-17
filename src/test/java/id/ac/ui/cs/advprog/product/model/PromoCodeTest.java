package id.ac.ui.cs.advprog.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.UUID;


public class PromoCodeTest {
  
  PromoCode promoCode;

    @BeforeEach
    void setUp() {
        promoCode = new Coupon();
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        promoCode.setId(id);
        promoCode.setName("ABADA100");
        promoCode.setDescription("Dapat digunakan kapanpun");
        promoCode.setExpirationDate("12/04/2023");
        promoCode.setMinimumPurchase(Double.valueOf(10000));
    }

    @Test
    void testGetPromoCodeID() {
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(id, promoCode.getId());
    }

    @Test
    void testGetPromoCodeName() {
        assertEquals("ABADA100", promoCode.getName());
    }

    @Test
    void testGetPromoCOdeDescription() {
        assertEquals("Dapat digunakan kapanpun", 
            promoCode.getDescription()
        );
    }

    @Test
    void testGetPromoCodeExpiredDate() {
        assertEquals("12/04/2023", 
            promoCode.getExpiredDate()
        );
    }

    @Test
    void testGetPromoCodeMinimumPurchase() {
        assertEquals(Double.valueOf(10000), 
            promoCode.getMinimumPurchase()
        );
    }

    @Test
    void testSetPromoCodeNameIfValid() {
        promoCode.setName("JELASVALID100");
        assertEquals("JELASVALID100", promoCode.getName());
    }

    @Test
    void testSetCouponNameIfInvalid() throws Exception{
        assertThrows(Exception.class, 
            () -> {promoCode.setName("invalid*Y$!)");
        });
    }

    
    @Test
    void testSetExpiredDateIfValid() {
        promoCode.setExpiredDate("11/12/2023");
        assertEquals("11/12/2023", promoCode.getExpiredDate());
    }

    
    @Test
    void testSetExpiredDateIfInvalid() throws Exception {
        assertThrows(Exception.class, 
            () -> {promoCode.setExpiredDate("invalid");
        });
    }
}
