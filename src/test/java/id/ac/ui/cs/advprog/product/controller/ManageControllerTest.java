package id.ac.ui.cs.advprog.product.controller;
import id.ac.ui.cs.advprog.product.service.ManageService;
import id.ac.ui.cs.advprog.product.service.StatsisticService;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
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

  @MockBean
  @Qualifier("promoCodeService")
  ManageService<PromoCode> promoService;

  @MockBean
  StatsisticService statisticService;

  List<Product> products;
  List<PromoCode> promoCodes;
  ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
    .configure(SerializationFeature.WRAP_ROOT_VALUE, false);
  ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

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
  void testGetAllProduct() throws Exception {
    doReturn(CompletableFuture.completedFuture(products)).when(productService).findAll();
    mockMvc.perform(get("/product/list"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].productName", is(products.get(0).getProductName())))
      .andExpect(jsonPath("$[1].productName", is(products.get(1).getProductName())));
  }
  @Test
  void testGetProductById() throws Exception {
    Product product = products.getFirst();
    doReturn(CompletableFuture.completedFuture(product)).when(productService).findById(ArgumentMatchers.any());

    mockMvc.perform(get("/product/id"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.productName", is(product.getProductName())));
  }

  @Test
  void testCreateProductPost() throws Exception {
    Product product = products.getFirst();
    doReturn(CompletableFuture.completedFuture(product)).when(productService).create(ArgumentMatchers.any());

    String request = writer.writeValueAsString(product);
    mockMvc.perform(post("/product/add")
      .contentType(MediaType.APPLICATION_JSON)
      .content(request))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.productName", is(product.getProductName())));
    
    verify(productService, times(1))
      .create(ArgumentMatchers.any());
  }

  @Test
  void testEditProductPost() throws Exception {
    Product product = products.getFirst();
    doReturn(CompletableFuture.completedFuture(product)).when(productService).edit(ArgumentMatchers.any() ,ArgumentMatchers.any());

    String request = writer.writeValueAsString(product);
    mockMvc.perform(put("/product/edit/id")
      .contentType(MediaType.APPLICATION_JSON)
      .content(request))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.productName", is(product.getProductName())));
    
    verify(productService, times(1))
      .edit(ArgumentMatchers.any(), ArgumentMatchers.any());
  }

  @Test
  void testDeleteProduct() throws Exception {
    Product product = products.getFirst();
    doReturn(CompletableFuture.completedFuture(product)).when(productService).delete(ArgumentMatchers.any());
    
    mockMvc.perform(delete("/product/delete/id"))
      .andExpect(status().isOk());

    verify(productService, times(1))
      .delete(ArgumentMatchers.any());
  }

  @Test
  void testGetAllPromoCode() throws Exception {
    doReturn(CompletableFuture.completedFuture(promoCodes)).when(promoService).findAll();

    mockMvc.perform(get("/promo_code/list"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].name", is(promoCodes.get(0).getName())))
      .andExpect(jsonPath("$[1].name", is(promoCodes.get(1).getName()))); 
  }

  @Test
  void testGetPromoById() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(CompletableFuture.completedFuture(promoCode)).when(promoService).findById(ArgumentMatchers.any());

    mockMvc.perform(get("/promo_code/anyid"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is(promoCode.getName())));
  }

  @Test
  void testCreatePromoCodePost() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(CompletableFuture.completedFuture(promoCode)).when(promoService).create(ArgumentMatchers.any());

    String request = writer.writeValueAsString(promoCode);
    mockMvc.perform(post("/promo_code/add")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding("utf-8")
      .content(request))
      .andExpect(status().isCreated());
    
    verify(promoService, times(1))
      .create(ArgumentMatchers.any());
  }

  @Test
  void testEditPromoCodePost() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(CompletableFuture.completedFuture(promoCode)).when(promoService).edit(ArgumentMatchers.any() , ArgumentMatchers.any());

    String request = writer.writeValueAsString(promoCode);
    System.out.println(request);
    mockMvc.perform(put("/promo_code/edit/id")
      .contentType(MediaType.APPLICATION_JSON)
      .content(request))
      .andExpect(status().isOk());
    
    verify(promoService, times(1))
      .edit(ArgumentMatchers.any(), ArgumentMatchers.any());
  }

  @Test
  void testDeletePromoCode() throws Exception {
    PromoCode promoCode = promoCodes.getFirst();
    doReturn(CompletableFuture.completedFuture(promoCode)).when(promoService).delete(ArgumentMatchers.any());

    mockMvc.perform(delete("/promo_code/delete/id"))
      .andExpect(status().isOk());

    verify(promoService, times(1))
      .delete(ArgumentMatchers.any());
  }

  @Test
  void testGetTopTenPage() throws Exception {
    doReturn(CompletableFuture.completedFuture(products)).when(statisticService).findBestTen();
    mockMvc.perform(get("/product/top10"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].productName", is(products.get(0).getProductName())))
      .andExpect(jsonPath("$[1].productName", is(products.get(1).getProductName())));
  }

  @Test
  void testGetWorstTenPage() throws Exception {
    doReturn(CompletableFuture.completedFuture(products)).when(statisticService).findWorstTen();
    mockMvc.perform(get("/product/worst10"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].productName", is(products.get(0).getProductName())))
      .andExpect(jsonPath("$[1].productName", is(products.get(1).getProductName())));
  }
}
