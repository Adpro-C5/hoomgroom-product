package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.product.repository.ManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements CatalogueService {

    @Autowired
    @Qualifier("productRepository")
    ManageRepository<Product> repository;
    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = repository.findAll();
        List<Product> products = new ArrayList<Product>();
        productIterator.forEachRemaining(products::add);
        return products;
    }

    @Override
    @Async("asyncTaskExecutor")
    public CompletableFuture<Product> findProductDetail(String productID) {
        return CompletableFuture.completedFuture(repository.findById(productID));
    }

    @Override
    @Async("asyncTaskExecutor")
    public CompletableFuture<List<Product>> showFilteredProduct(String filterType) {
        List<Product> products = this.findAll();
        List<Product> filteredProducts = new ArrayList<>();
        switch (filterType) {
            case "Chairs":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Chairs")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Tables":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Tables")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Storage":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Storage")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Decoration":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Decoration")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Bedding":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Bedding")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Minimum price":
                filteredProducts.addAll(products);
                Collections.sort(filteredProducts, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                break;
            case "Maximum price":
                filteredProducts.addAll(products);
                Collections.sort(filteredProducts, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                break;
        }
        return CompletableFuture.completedFuture(filteredProducts);
    }
}
