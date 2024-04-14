package id.ac.ui.cs.advprog.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class ProductTest {

    product Product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Kursi Mewah");

        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Kursi");
        categories.add("Kayu");

        this.product.setProductCategories(categories);
        this.product.setProductDescription("Sebuah kursi yang dibuat dengan kayu jati");
        this.product.setProductImagePath("/sampel");
        this.product.setProductPrice(10000);
        this.product.setProductDiscountedPrice(8000);
        this.product.setProductSales(1000);
    }

    @Test
    void testGetProductID() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", 
            this.product.getID()
        );
    }

    @Test
    void testGetProductName() {
        assertEquals("Kursi Mewah", this.product.getProductNameID());
    }

    @Test
    void testGetProductCategories() {
        ArrayList<String> result = this.Product.getProductCategories();
        assertEquals("Kursi", result[0]);
        assertEquals("Kayu", result[1]);
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Sebuah kursi yang dibuat dengan kayu jati", 
            this.product.getProductDescription()
        );
    }

    @Test
    void testGetProductImagePath() {
        assertEquals("/sampel", this.product.getProductImagePath());
    }

    @Test
    void testGetProductPrice() {
        assertEquals(10000, this.product.getProductPrice());
    }

    @Test
    void testGetProductDiscountedPrice() {
        assertEquals(8000, this.product.getProductDiscountedPrice());
    }

    @Test
    void testGetProductSales() {
        assertEquals(1000, this.product.getProductSales());
    }
}
