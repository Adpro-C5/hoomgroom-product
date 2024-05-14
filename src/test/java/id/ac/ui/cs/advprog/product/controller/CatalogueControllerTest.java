package id.ac.ui.cs.advprog.product.controller;

import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.service.CatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CatalogueControllerTest {

    @Mock
    private CatalogueService catalogueService;

    @InjectMocks
    private CatalogueController catalogueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProductCataloguePage() {
        List<Product> products = new ArrayList<>();
        products.add(new Product()); // Add dummy product

        when(catalogueService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = catalogueController.productCataloguePage();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testProductDetailPage() {
        String productId = "123";
        Product product = new Product(); // Add dummy product
        when(catalogueService.findProductDetail(productId)).thenReturn(product);

        ResponseEntity<Product> response = catalogueController.productDetailPage(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testProductDetailPageNotFound() {
        String productId = "123";
        when(catalogueService.findProductDetail(productId)).thenReturn(null);

        ResponseEntity<Product> response = catalogueController.productDetailPage(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testProductFilteredPage() {
        String filterType = "actor";
        List<Product> products = new ArrayList<>();
        products.add(new Product()); // Add dummy product

        when(catalogueService.showFilteredProduct(filterType)).thenReturn(products);

        ResponseEntity<List<Product>> response = catalogueController.productFilteredPage(filterType);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}

