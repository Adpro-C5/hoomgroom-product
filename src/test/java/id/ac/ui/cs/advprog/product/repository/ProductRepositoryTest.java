package id.ac.ui.cs.advprog.product.repository;
import id.ac.ui.cs.advprog.product.model.Product;
import jakarta.transaction.Transactional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
public class ProductRepositoryTest {
  @Autowired
  ProductRepository productRepository;

  List<Product> products;
  
  @BeforeEach
  void setUp() {
    Product product1 = new Product();
    UUID id = UUID.randomUUID();
    product1.setId(id);
    product1.setProductName("Kursi Mewah");

    ArrayList<String> categories1 = new ArrayList<String>();
    categories1.add("Kursi");
    categories1.add("Kayu");

    product1.setCategories(categories1);
    product1.setDescription("Sebuah kursi yang dibuat dengan kayu jati");
    product1.setImagePath("/sampel");
    product1.setPrice(Double.valueOf(10000));
    product1.setDiscountedPrice(Double.valueOf(8000));
    product1.setSales(Integer.valueOf(1000));

    Product product2 = new Product();
    UUID id2 = UUID.randomUUID();
    product2.setId(id2);
    product2.setProductName("Meja Mewah");

    ArrayList<String> categories2 = new ArrayList<String>();
    categories2.add("Meja");
    categories2.add("Marbel");

    product2.setCategories(categories2);
    product2.setDescription("Sebuah meja yang dibuat dengan marbel impor");
    product2.setImagePath("/sampel2");
    product2.setPrice(Double.valueOf(30000));
    product2.setDiscountedPrice(Double.valueOf(24000));
    product2.setSales(Integer.valueOf(500));

    products = new ArrayList<Product>();
    products.add(product1);
    products.add(product2);
  }

  @Test
  void testSaveProduct() {
    Product product = products.get(0);
    Product result = productRepository.save(product);

    assertEquals(product.getId(), result.getId());
    assertEquals(product.getProductName(), result.getProductName());
    assertEquals(product.getCategories(), result.getCategories());
    assertEquals(product.getDescription(), result.getDescription());
    assertEquals(product.getImagePath(), result.getImagePath());
    assertEquals(product.getPrice(), result.getPrice());
    assertEquals(product.getDiscountedPrice(), result.getDiscountedPrice());
    assertEquals(product.getSales(), result.getSales());
  }

  @Test
  void testSaveProductUpdate() {
    Product product = products.get(0);
    Product updatedProduct = products.get(1);
    updatedProduct.setId(product.getId());
    updatedProduct.setSales(10000);

    productRepository.save(product);
    Product result = productRepository.save(updatedProduct);

    assertEquals(product.getId(), result.getId());
    assertEquals(updatedProduct.getProductName(), result.getProductName());
    assertEquals(updatedProduct.getSales(), result.getSales());
  }

  @Test
  void testFindAll() {
    Product product1 = products.get(0);
    Product product2 = products.get(1);
    productRepository.save(product1);
    productRepository.save(product2);

    Iterator<Product> results = productRepository.findAll();
    assertEquals(product1.getId(), results.next().getId());
    assertEquals(product2.getId(), results.next().getId());
  }

  @Test
  void testFindById() {
    Product product1 = products.get(0);
    Product product2 = products.get(1);
    productRepository.save(product1);
    productRepository.save(product2);

    Product result = productRepository.findById(product2.getId().toString());
    assertEquals(product2.getId(), result.getId());
  }

  @Test
  void testDeleteById() {
    Product product1 = products.get(0);
    productRepository.save(product1);
    productRepository.deleteById(product1.getId().toString());
    Iterator<Product> result = productRepository.findAll();
    assertFalse(result.hasNext());
  }

  @Test
  void testGetBestTen() {
    Product product1 = products.getFirst();
    Product product2 = products.getLast();
    productRepository.save(product1);
    productRepository.save(product2);
    Iterator<Product> result = productRepository.getBestTen();
    assertEquals(result.next().getProductName(), product1.getProductName());
    assertEquals(result.next().getProductName(), product2.getProductName());
  }

  @Test
  void testGetWorstTen() {
    Product product1 = products.getFirst();
    Product product2 = products.getLast();
    productRepository.save(product1);
    productRepository.save(product2);
    Iterator<Product> result = productRepository.getWorstTen();
    assertEquals(result.next().getProductName(), product2.getProductName());
    assertEquals(result.next().getProductName(), product1.getProductName());
  }
}
