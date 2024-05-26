package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogueServiceTest {
    @InjectMocks
    CatalogueServiceImpl catalogueService;

    @Mock
    ProductRepository productRepository;

    List<Product> products;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        UUID id = UUID.randomUUID();
        ArrayList<String> productCategories1 = new ArrayList<>();
        productCategories1.add("Chairs");
        product1.setId(id);
        product1.setProductName("Kursi Mewah");
        product1.setCategories(productCategories1);
        product1.setSales(Integer.valueOf(1000));

        Product product2 = new Product();
        UUID id2 = UUID.randomUUID();
        ArrayList<String> productCategories2 = new ArrayList<>();
        productCategories2.add("Meja");
        product2.setId(id2);
        product2.setProductName("Meja Mewah");
        product2.setCategories(productCategories2);
        product2.setSales(Integer.valueOf(500));

        products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
    }

    @Test
    void testFindAll() {
        doReturn(products.iterator()).when(productRepository)
                .findAll();

        List<Product> result = catalogueService.findAll();
        verify(productRepository, times(1)).findAll();
        assertEquals(products.get(0).getId(), result.get(0).getId());
    }

    @Test
    void testFindProductDetail() throws Exception {
        UUID productId = products.get(0).getId();

        when(productRepository.findById(productId.toString())).thenReturn(products.get(0));

        Product result = catalogueService.findProductDetail(productId.toString()).get();

        verify(productRepository, times(1)).findById(productId.toString());
        assertEquals(products.get(0), result);
    }

    @Test
    void testShowFilteredProduct() throws Exception {
        String filterType = "Chairs";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, filteredProducts.size());
        assertEquals("Kursi Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Chairs", filteredProducts.get(0).getCategories().get(0));
    }
}
