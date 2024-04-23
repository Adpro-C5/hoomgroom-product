package id.ac.ui.cs.advprog.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.product.repository.ProductRepository;
import id.ac.ui.cs.advprog.product.model.Product;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceImplTest {
  @InjectMocks
  StatisticServiceImpl statisticService;

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
  void testFindBestTen() {
    Product product3 = new Product();
    product3.setId(UUID.randomUUID());
    product3.setSales(Integer.valueOf(1200));
    Product product4 = new Product();
    product4.setId(UUID.randomUUID());
    product4.setSales(50);;
    products.add(product3);
    products.add(product4);

    doReturn(products.iterator()).when(productRepository).findAll();

    List<Product> result = statisticService.findBestTen();
    verify(productRepository, times(1)).findAll();
    assertEquals(product3.getSales(), result.getFirst().getSales());
    assertEquals(product4.getSales(), result.getLast().getSales());
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

    doReturn(products.iterator()).when(productRepository).findAll();

    List<Product> result = statisticService.findWorstTen();
    verify(productRepository, times(1)).findAll();
    assertEquals(product4.getSales(), result.getFirst().getSales());
    assertEquals(product3.getSales(), result.getLast().getSales());
  }
}
