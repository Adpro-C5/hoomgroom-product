public package id.ac.ui.cs.advprog.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.product.repository.ProductRepository;
import id.ac.ui.cs.advprog.product.model.Product;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
  @InjectMocks 
  ProductService productService;

  @Mock
  ProductRepository productRepository;

  List<Product> products;

  @BeforeEach
  void setUp() {
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
  }

  @Test
  void testCreateProduct() {
    Product product = products.get(0);
    doReturn(product).when(productRepository).save(product);

    Product result = productService.createProduct(product);
    verify(productRepository, times(1)).save(product);
    assertEquals(product.getId(), result.getId());
  }

  @Test
  void testEditProduct() {
    Product product = products.get(0);
    Product editedProduct = products.get(1);
    editedProduct.setId(product.getId());
    doReturn(editedProduct).when(productRepository)
      .save(editedProduct);

    Product result = productService
     .editProduct(product.getId(), editedProduct);
    verify(productRepository, times(1)).save(product);
    assertEquals(product.getId(), result.getId());
  }

  @Test 
  void testFindById() {
    Product product = products.get(0);
    doReturn(product).when(productRepository)
      .findById(product.getId().toString());

    Product result = productService
      .findById(product.getId().toString());
    verify(productRepository, times(1))
      .findById(product.getId().toString());
    assertEquals(product.getId(), result.getId());
  }

  @Test
  void testDelete() {
    Product product = products.get(0);
    doReturn(product).when(productRepository)
      .deleteById(product.getId().toString());

    Product result = productService
      .delete(product.getId().toString());
    verify(productRepository, times(1))
      .deleteById(product.getId().toString());
    assertEquals(product.getId(), result.getId());
  }

  @Test
  void testFindAll() {
    doReturn(products).when(productRepository)
      .findAll();

    List<Product> result = productService.findAll();
    verify(productRepository, times(1)).findAll();
    assertEquals(products.get(0).getId(), result.get(0).getId());
  }

  @Test
  void testFindBestTen() {
    Product product3 = new Product();
    product3.setId(UUID.randomUUID());
    product3.setSales(Integer.valueOf(1200));
    Product product4 = new Product();
    product4.setId(UUID.randomUUID());
    product4.setSales(50);;
    products.add(product3);
    products.add(product4);

    doReturn(products).when(productRepository).findAll();

    List<Product> result = productService.findBestTen();
    verify(productRepository, times(1)).findAll();
    assertEquals(product3, result.getFirst());
    assertEquals(product4, result.getLast());
  }

  @Test
  void testFindWorstTen() {
    Product product3 = new Product();
    product3.setId(UUID.randomUUID());
    product3.setSales(Integer.valueOf(1200));
    Product product4 = new Product();
    product4.setId(UUID.randomUUID());
    product4.setSales(50);;
    products.add(product3);
    products.add(product4);

    doReturn(products).when(productRepository).findAll();

    List<Product> result = productService.findWorstTen();
    verify(productRepository, times(1)).findAll();
    assertEquals(product4, result.getFirst());
    assertEquals(product3, result.getLast());
  }
}