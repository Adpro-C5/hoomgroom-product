package id.ac.ui.cs.advprog.product.controller;

import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class CatalogueController {

    @Autowired
    CatalogueService catalogueService;

    @PostMapping("/product/catalogue")
    public ResponseEntity<List<Product>> productCataloguePage() {
        List<Product> allProducts = catalogueService.findAll();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/product/details/{productId}")
    public ResponseEntity<Product> productDetailPage(@PathVariable("productId") String productId) {
        try {
            CompletableFuture<Product> product = catalogueService.findProductDetail(productId);
            if (product != null) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/product/{filterType}/")
    public ResponseEntity<List<Product>> productFilteredPage(@PathVariable("filterType") String filterType) {
        try {
            List<Product> filteredProducts = catalogueService.showFilteredProduct(filterType).get();
            return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}