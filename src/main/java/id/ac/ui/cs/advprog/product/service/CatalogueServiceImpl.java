package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.product.repository.ManageRepository;
import id.ac.ui.cs.advprog.product.repository.ProductRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements CatalogueService {

    @Autowired
    @Qualifier("productRepository")
    ProductRepositoryInterface repository;
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
            case "Kursi":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Kursi")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Meja":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Meja")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Penyimpanan":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Penyimpanan")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Dekorasi":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Dekorasi")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Ranjang":
                for (Product tempProduct: products) {
                    ArrayList<String> categories = tempProduct.getCategories();
                    for (String category : categories) {
                        if (category.equals("Ranjang")) {
                            filteredProducts.add(tempProduct);
                            break;
                        }
                    }
                }
                break;
            case "Harga-Minimal":
                filteredProducts.addAll(products);
                Collections.sort(filteredProducts, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                break;
            case "Harga-Maksimal":
                filteredProducts.addAll(products);
                Collections.sort(filteredProducts, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                break;
            default:
                filteredProducts = products;
                break;
        }
        return CompletableFuture.completedFuture(filteredProducts);
    }
}
