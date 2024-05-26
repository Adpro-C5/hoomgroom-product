package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CatalogueService {
    List<Product> findAll();
    CompletableFuture<Product> findProductDetail(String productID);
    CompletableFuture<List<Product>> showFilteredProduct(String filterType);
}
