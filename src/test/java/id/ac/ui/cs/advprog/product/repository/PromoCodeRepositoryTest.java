package id.ac.ui.cs.advprog.product.repository;
import id.ac.ui.cs.advprog.product.model.PromoCode;
import jakarta.transaction.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
public class PromoCodeRepositoryTest {
  @Autowired
  PromoCodeRepository promoCodeRepository;
  
  List<PromoCode> promoCodes;

  @BeforeEach
  void setUp() throws Exception {
    PromoCode promoCode1 = new PromoCode();
    UUID id = UUID.randomUUID();
    promoCode1.setId(id);
    promoCode1.setName("ABADA10");
    promoCode1.setDescription("Dapat digunakan kapanpun");
    promoCode1.setExpiredDate(LocalDate.of(2030, 12, 12));
    promoCode1.setMinimumPurchase(Double.valueOf(10000));

    PromoCode promoCode2 = new PromoCode(); 
    UUID id2 = UUID.randomUUID();
    promoCode2.setId(id2);
    promoCode2.setName("BONUSDISKON10");
    promoCode2.setDescription("Spesial diskon");
    promoCode2.setExpiredDate(LocalDate.of(2035, 12, 12));
    promoCode2.setMinimumPurchase(Double.valueOf(1000));
    
    promoCodes = new ArrayList<PromoCode>();
    promoCodes.add(promoCode1);
    promoCodes.add(promoCode2);
  }

  @Test
  void testSavePromoCode() {
    PromoCode promoCode = promoCodes.getFirst();
    PromoCode result = promoCodeRepository.save(promoCode);

    assertEquals(promoCode.getId(), result.getId());
    assertEquals(promoCode.getName(), result.getName());
    assertEquals(promoCode.getDescription(), result.getDescription());
    assertEquals(promoCode.getExpiredDate(), result.getExpiredDate());
    assertEquals(promoCode.getMinimumPurchase(), result.getMinimumPurchase());
  }

  @Test
  void testSaveProductUpdate() {
    PromoCode promoCode = promoCodes.getFirst();
    PromoCode updatedPromoCode = promoCodes.getLast();
    updatedPromoCode.setId(promoCode.getId());

    promoCodeRepository.save(promoCode);
    PromoCode result = promoCodeRepository.save(updatedPromoCode);

    assertEquals(promoCode.getId(), result.getId());
    assertEquals(updatedPromoCode.getName(), result.getName());
  }

  @Test
  void testFindAll() {
    PromoCode promoCode1 = promoCodes.getFirst();
    PromoCode promoCode2 = promoCodes.getLast();
    
    promoCodeRepository.save(promoCode1);
    promoCodeRepository.save(promoCode2);

    Iterator<PromoCode> results = promoCodeRepository.findAll();
    assertEquals(promoCode1.getId(), results.next().getId());
    assertEquals(promoCode2.getId(), results.next().getId());
  }

  @Test
  void testFindById() {
    PromoCode promoCode1 = promoCodes.getFirst();
    PromoCode promoCode2 = promoCodes.getLast();

    promoCodeRepository.save(promoCode1);
    promoCodeRepository.save(promoCode2);

    PromoCode result = promoCodeRepository
      .findById(promoCode2.getId().toString());
    assertEquals(promoCode2.getId(), result.getId());
  }

  @Test
  void testDeleteById() {
    PromoCode promoCode = promoCodes.getFirst();

    promoCodeRepository.save(promoCode);
    promoCodeRepository.deleteById(promoCode.getId().toString());
    Iterator<PromoCode> result = promoCodeRepository.findAll();
    assertFalse(result.hasNext());
  }

  @Test
  void testFindByName() {
    PromoCode promoCode1 = promoCodes.getFirst();
    PromoCode promoCode2 = promoCodes.getFirst();
    promoCodeRepository.save(promoCode1);
    promoCodeRepository.save(promoCode2);
    PromoCode result = promoCodeRepository.findByName(promoCode2.getName());
    assertEquals(promoCode2, result);
  }
}
