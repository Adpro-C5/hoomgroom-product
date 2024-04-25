package id.ac.ui.cs.advprog.product.controller;
import id.ac.ui.cs.advprog.product.service.ManageService;
import id.ac.ui.cs.advprog.product.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManageController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ManageControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  @Qualifier("productService")
  ManageService<Product> manageService;

  @Test
  void testGetProductPage() throws Exception {
    mockMvc.perform(get("/product/list"))
      .andExpect(status().isOk());
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
