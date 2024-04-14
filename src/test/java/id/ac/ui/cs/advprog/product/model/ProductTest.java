package id.ac.ui.cs.advprog.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.UUID;

public class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setId(id);
        this.product.setProductName("Kursi Mewah");

        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Kursi");
        categories.add("Kayu");

        this.product.setCategories(categories);
        this.product.setDescription("Sebuah kursi yang dibuat dengan kayu jati");
        this.product.setImagePath("/sampel");
        this.product.setPrice(Double.valueOf(10000));
        this.product.setDiscountedPrice(Double.valueOf(8000));
        this.product.setSales(Integer.valueOf(1000));
    }

    @Test
    void testGetProductID() {
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(id,  this.product.getId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Kursi Mewah", this.product.getProductName());
    }

    @Test
    void testGetProductCategories() {
        ArrayList<String> result = this.product.getCategories();
        assertEquals("Kursi", result.get(0));
        assertEquals("Kayu", result.get(1));
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Sebuah kursi yang dibuat dengan kayu jati", 
            this.product.getDescription()
        );
    }

    @Test
    void testGetProductImagePath() {
        assertEquals("/sampel", this.product.getImagePath());
    }

    @Test
    void testGetProductPrice() {
        assertEquals(10000, this.product.getPrice());
    }

    @Test
    void testGetProductDiscountedPrice() {
        assertEquals(8000, this.product.getDiscountedPrice());
    }

    @Test
    void testGetProductSales() {
        assertEquals(1000, this.product.getSales());
    }
}
