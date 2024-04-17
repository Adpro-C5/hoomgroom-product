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
  ManageService<Product> manageService;

  @Test
  void testGetProductPage() {
    mockMvc.perform(get("/product/list"))
      .andExpect(status().isOk());
  }

  @Test
  void testCreateProductPage() {
    mockMvc.perform(get("/product/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testCreateProductPost() {
    mockMvc.perform(post("/product/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testEditProductPage() {
    mockMvc.perform(get("/product/edit"))
      .andExpect(status().isOk());    
  }

  @Test
  void testEditProductPost() {
    mockMvc.perform(post("/product/edit"))
      .andExpect(status().isOk());
  }

  @Test
  void testDeleteProduct() {
    mockMvc.perform(post("/product/delete"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetPromoCodePage() {
    mockMvc.perform(get("/promo_code/list"))
      .andExpect(status().isOk());
  }

  @Test
  void testCreatePromoCodePage() {
    mockMvc.perform(get("/promo_code/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testCreatePromoCodePost() {
    mockMvc.perform(post("/promo_code/add"))
      .andExpect(status().isOk());
  }

  @Test
  void testEditPromoCodePage() {
    mockMvc.perform(get("/promo_code/edit"))
      .andExpect(status().isOk());
  }

  @Test
  void testEditPromoCodePost() {
    mockMvc.perform(post("/promo_code/edit"))
      .andExpect(status().isOk());
  }

  @Test
  void testDeletePromoCodePage() {
    mockMvc.perform(get("/promo_code/delete"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetTopTenPage() {
    mockMvc.perform(get("/product/top10"))
      .andExpect(status().isOk());
  }

  @Test
  void testGetWorstTenPage() {
    mockMvc.perform(get("/product/worst10"))
      .andExpect(status().isOk());
  }
}
