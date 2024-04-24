package id.ac.ui.cs.advprog.product.controller;
import id.ac.ui.cs.advprog.product.service.ManageService;
import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.model.PromoCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDate;

@WebMvcTest(ManageController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ManageControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  @Qualifier("productService")
  ManageService<Product> productService;

  List<Product> products;
  List<PromoCode> promoCodes;

  @BeforeEach
  void setUp() throws Exception {
    Product product1 = new Product();
    UUID id = UUID.randomUUID();
    product1.setId(id);
    product1.setProductName("Kursi Mewah");
    product1.setSales(Integer.valueOf(1000));

    Product product2 = new Product();
    UUID id2 = UUID.randomUUID();
    product2.setId(id2);
    product2.setProductName("Meja Mewah");
    product2.setSales(Integer.valueOf(500));

    products = new ArrayList<Product>();
    products.add(product1);
    products.add(product2);

    PromoCode promoCode1 = new PromoCode();
    UUID id3 = UUID.randomUUID();
    promoCode1.setId(id3);
    promoCode1.setName("ABADA10");
    promoCode1.setDescription("Dapat digunakan kapanpun");
    promoCode1.setExpiredDate(LocalDate.of(2030, 12, 12));
    promoCode1.setMinimumPurchase(Double.valueOf(10000));

    PromoCode promoCode2 = new PromoCode(); 
    UUID id4 = UUID.randomUUID();
    promoCode2.setId(id4);
    promoCode2.setName("BONUSDISKON10");
    promoCode2.setDescription("Spesial diskon");
    promoCode2.setExpiredDate(LocalDate.of(2035, 12, 12));
    promoCode2.setMinimumPurchase(Double.valueOf(1000));
    
    promoCodes = new ArrayList<PromoCode>();
    promoCodes.add(promoCode1);
    promoCodes.add(promoCode2);  
  }

  @Test
  void testGetProductPage() throws Exception {
    mockMvc.perform(get("/product/list"))
      .andExpect(status().isOk());
  }
  @Test
  void testGetProductById() throws Exception {
    Product product = products.getFirst();
    doReturn(product).when(productService).findById(ArgumentMatchers.any());

    mockMvc.perform(get("/product/{id}"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is(product.getProductName())));
  }

  @Test
  void testCreateProductPage() throws Exception {
    mockMvc.perform(get("/product/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testCreateProductPost() throws Exception {
    mockMvc.perform(post("/product/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testEditProductPage() throws Exception {
    mockMvc.perform(get("/product/edit"))
      .andExpect(status().isOk());    
  }

  @Test
  void testEditProductPost() throws Exception {
    mockMvc.perform(post("/product/edit"))
      .andExpect(status().isOk());
  }

  @Test
  void testDeleteProduct() throws Exception {
    mockMvc.perform(post("/product/delete"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetPromoCodePage() throws Exception {
    mockMvc.perform(get("/promo_code/list"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetPromoById() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(promoCode).when(productService).findById(ArgumentMatchers.any());

    mockMvc.perform(get("/promo_code/{id}"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is(promoCode.getName())));
  }

  @Test
  void testCreatePromoCodePage() throws Exception {
    mockMvc.perform(get("/promo_code/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testCreatePromoCodePost() throws Exception {
    mockMvc.perform(post("/promo_code/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testEditPromoCodePage() throws Exception {
    mockMvc.perform(get("/promo_code/edit"))
      .andExpect(status().isOk());
  }

  @Test
  void testEditPromoCodePost() throws Exception {
    mockMvc.perform(post("/promo_code/edit"))
      .andExpect(status().isOk());
  }

  @Test
  void testDeletePromoCodePage() throws Exception {
    mockMvc.perform(post("/promo_code/delete"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetTopTenPage() throws Exception {
    mockMvc.perform(get("/product/top10"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetWorstTenPage() throws Exception {
    mockMvc.perform(get("/product/worst10"))
      .andExpect(status().isOk());
  }
}
