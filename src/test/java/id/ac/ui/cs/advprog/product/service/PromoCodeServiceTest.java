package id.ac.ui.cs.advprog.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.product.repository.PromoCodeRepository;
import id.ac.ui.cs.advprog.product.model.PromoCode;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PromoCodeServiceTest {
  @InjectMocks 
  PromoCodeService promoCodeService;

  @Mock
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
  void testCreateProductIfValid() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(new ArrayList<PromoCode>().iterator())
      .when(promoCodeRepository).findAll();
    doReturn(promoCode).when(promoCodeRepository).save(promoCode);

    PromoCode result = promoCodeService.create(promoCode).get();
    verify(promoCodeRepository, times(1)).save(promoCode);
    assertEquals(promoCode.getId(), result.getId());
  }

  
  @Test
  void testCreateProductIfDuplicateName() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(promoCodes.iterator()).when(promoCodeRepository).findAll();

    assertThrows(Exception.class, () -> {
    promoCodeService.create(promoCode);
    });
    verify(promoCodeRepository, times(0)).save(promoCode);
  }

  @Test
  void testEditProductIfValid() throws NoSuchElementException, InterruptedException, ExecutionException {
    PromoCode promoCode = promoCodes.getFirst();
    PromoCode editedPromo = promoCodes.getLast();
    editedPromo.setId(promoCode.getId());
    doReturn(new ArrayList<PromoCode>().iterator())
      .when(promoCodeRepository).findAll();
    doReturn(editedPromo).when(promoCodeRepository)
      .save(editedPromo);
    doReturn(promoCode).when(promoCodeRepository)
      .findById(promoCode.getId().toString());

    PromoCode result = promoCodeService
      .edit(promoCode.getId().toString(), editedPromo).get();
    verify(promoCodeRepository, times(1))
      .findById(promoCode.getId().toString());
    verify(promoCodeRepository, times(1))
      .save(editedPromo);
    assertEquals(promoCode.getId(), result.getId());
    assertEquals(editedPromo.getId(), result.getId());
  }

  @Test
  void testEditProductIfDuplicateName() {
    PromoCode promoCode = promoCodes.getFirst();
    PromoCode editedPromo = promoCodes.getLast();
    editedPromo.setId(promoCode.getId());
    doReturn(promoCodes.iterator()).when(promoCodeRepository)
      .findAll();

    assertThrows(Exception.class, () -> {
    promoCodeService.edit(promoCode.getId().toString() ,promoCode);
    });
    verify(promoCodeRepository, times(0)).save(promoCode);
  }

  @Test 
  void testFindById() throws InterruptedException, ExecutionException {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(promoCode).when(promoCodeRepository)
      .findById(promoCode.getId().toString());

    PromoCode result = promoCodeService
      .findById(promoCode.getId().toString()).get();
    verify(promoCodeRepository, times(1))
      .findById(promoCode.getId().toString());
    assertEquals(promoCode.getId(), result.getId());
  }

  @Test
  void testDelete() throws NoSuchElementException, InterruptedException, ExecutionException {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(promoCode).when(promoCodeRepository)
      .deleteById(promoCode.getId().toString());
    doReturn(promoCode).when(promoCodeRepository)
      .findById(promoCode.getId().toString());

    PromoCode result = promoCodeService
      .delete(promoCode.getId().toString()).get();
    verify(promoCodeRepository, times(1))
      .deleteById(promoCode.getId().toString());
    assertEquals(promoCode.getId(), result.getId());
  }

  @Test
  void testFindAll() throws InterruptedException, ExecutionException {
    doReturn(promoCodes.iterator()).when(promoCodeRepository)
      .findAll();

    List<PromoCode> result = promoCodeService.findAll().get();
    verify(promoCodeRepository, times(1)).findAll();
    assertEquals(promoCodes.getFirst().getId(), result.getFirst().getId());
    assertEquals(promoCodes.getLast().getId(), result.getLast().getId());
  }

  @Test
  void testFindByName() throws InterruptedException, ExecutionException {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(promoCode).when(promoCodeRepository)
      .findByName(promoCode.getName());

    PromoCode result = promoCodeService
      .findByName(promoCode.getName()).get();
    verify(promoCodeRepository, times(1))
      .findByName(promoCode.getName());
    assertEquals(promoCode.getId(), result.getId());
  }
}
