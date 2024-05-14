package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;

import java.util.List;
import java.util.UUID;

public interface CatalogueService {
    List<Product> findAll();
    Product findProductDetail(String productID);
    List<Product> showFilteredProduct(String filterType);
}
