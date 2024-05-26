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
public class CatalogueServiceImplTest {
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
        product1.setPrice(1200000.00);
        product1.setCategories(productCategories1);
        product1.setSales(1000);

        Product product2 = new Product();
        UUID id2 = UUID.randomUUID();
        ArrayList<String> productCategories2 = new ArrayList<>();
        productCategories2.add("Tables");
        product2.setId(id2);
        product2.setProductName("Meja Mewah");
        product2.setPrice(1100000.00);
        product2.setCategories(productCategories2);
        product2.setSales(500);

        Product product3 = new Product();
        UUID id3 = UUID.randomUUID();
        ArrayList<String> productCategories3 = new ArrayList<>();
        productCategories3.add("Storage");
        product3.setId(id3);
        product3.setProductName("Lemari Mewah");
        product3.setPrice(1000000.00);
        product3.setCategories(productCategories3);
        product3.setSales(300);

        Product product4 = new Product();
        UUID id4 = UUID.randomUUID();
        ArrayList<String> productCategories4 = new ArrayList<>();
        productCategories4.add("Decoration");
        product4.setId(id4);
        product4.setProductName("Vas Bunga");
        product4.setPrice(900000.00);
        product4.setCategories(productCategories4);
        product4.setSales(200);

        Product product5 = new Product();
        UUID id5 = UUID.randomUUID();
        ArrayList<String> productCategories5 = new ArrayList<>();
        productCategories5.add("Bedding");
        product5.setId(id5);
        product5.setProductName("Sprei Mewah");
        product5.setPrice(800000.00);
        product5.setCategories(productCategories5);
        product5.setSales(400);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
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
    void testShowFilteredProductChairs() throws Exception {
        String filterType = "Chairs";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, filteredProducts.size());
        assertEquals("Kursi Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Chairs", filteredProducts.get(0).getCategories().get(0));
    }

    @Test
    void testShowFilteredProductTables() throws Exception {
        String filterType = "Tables";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, filteredProducts.size());
        assertEquals("Meja Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Tables", filteredProducts.get(0).getCategories().get(0));
    }

    @Test
    void testShowFilteredProductStorage() throws Exception {
        String filterType = "Storage";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, filteredProducts.size());
        assertEquals("Lemari Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Storage", filteredProducts.get(0).getCategories().get(0));
    }

    @Test
    void testShowFilteredProductDecoration() throws Exception {
        String filterType = "Decoration";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, filteredProducts.size());
        assertEquals("Vas Bunga", filteredProducts.get(0).getProductName());
        assertEquals("Decoration", filteredProducts.get(0).getCategories().get(0));
    }

    @Test
    void testShowFilteredProductBedding() throws Exception {
        String filterType = "Bedding";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, filteredProducts.size());
        assertEquals("Sprei Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Bedding", filteredProducts.get(0).getCategories().get(0));
    }

    @Test
    void testShowFilteredProductMinimumPrice() throws Exception {
        String filterType = "Minimum price";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(5, filteredProducts.size());
        assertEquals("Sprei Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Vas Bunga", filteredProducts.get(1).getProductName());
        assertEquals("Lemari Mewah", filteredProducts.get(2).getProductName());
        assertEquals("Meja Mewah", filteredProducts.get(3).getProductName());
        assertEquals("Kursi Mewah", filteredProducts.get(4).getProductName());
    }

    @Test
    void testShowFilteredProductMaximumPrice() throws Exception {
        String filterType = "Maximum price";

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();

        verify(productRepository, times(1)).findAll();

        assertEquals(5, filteredProducts.size());
        assertEquals("Kursi Mewah", filteredProducts.get(0).getProductName());
        assertEquals("Meja Mewah", filteredProducts.get(1).getProductName());
        assertEquals("Lemari Mewah", filteredProducts.get(2).getProductName());
        assertEquals("Vas Bunga", filteredProducts.get(3).getProductName());
        assertEquals("Sprei Mewah", filteredProducts.get(4).getProductName());
    }
}
