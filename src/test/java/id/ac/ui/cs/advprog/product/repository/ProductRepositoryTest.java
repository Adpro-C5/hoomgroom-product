package id.ac.ui.cs.advprog.product.repository;
import id.ac.ui.cs.advprog.product.model.Product;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
  ProductRepository productRepository;
  List<Product> products;

  @BeforeEach
  void setUp() {
    Product product1 = new Product();
    UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
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
    UUID id2 = UUID.fromString("eawpidhawaw-aluwblaa-aowndawb");
    product2.setId(id);
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

    Iterator<Product> results = productRepository.getAll();
    assertEquals(product1, results.next());
    assertEquals(product2, results.next());
  }

  @Test
  void testFindById() {
    Product product1 = products.get(0);
    Product product2 = products.get(1);

    productRepository.save(product1);
    productRepository.save(product2);

    Product result = productRepository.findById(product2.getId());
    assertEquals(product2, result);
  }

  @Test
  void testDeleteById() {
    Product product1 = products.get(0);

    productRepository.save(product1);
    productRepository.deleteById(product1.getId());
    Iterator<Product> result = productRepository.findAll();
    assertFalse(result.hasNext());
  }
}