package id.ac.ui.cs.advprog.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import java.time.LocalDate;
import org.springframework.test.context.ActiveProfiles;

public class PromoCodeTest {
  
  PromoCode promoCode;

    @BeforeEach
    void setUp() throws Exception {
        promoCode = new PromoCode();
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        promoCode.setId(id);
        promoCode.setName("ABADA100");
        promoCode.setDescription("Dapat digunakan kapanpun");
        promoCode.setExpiredDate(LocalDate.of(2030, 12, 12));
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
        assertEquals(LocalDate.of(2030, 12, 12), 
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
    void testSetPromoCodeNameIfValid() throws Exception {
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
        promoCode.setExpiredDate(LocalDate
            .of(2030, 12, 12));
        assertEquals(LocalDate.of(2030, 12, 12)
            , promoCode.getExpiredDate());
    }

    
    @Test
    void testSetExpiredDateIfInvalid() throws Exception {
        assertThrows(Exception.class, 
            () -> {promoCode.setExpiredDate(LocalDate.of(-2, -5, -10));
        });
    }
}
