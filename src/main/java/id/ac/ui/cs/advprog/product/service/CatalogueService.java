package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;

import java.util.List;

public interface CatalogueService {
    List<Product> findAll();
    Product findProductDetail();
    List<Product> showFilteredProduct();
}
