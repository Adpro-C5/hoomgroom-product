package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import id.ac.ui.cs.advprog.product.repository.ManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public Product findProductDetail(String productID) {
        return repository.findById(productID);
    }

    @Override
    public List<Product> showFilteredProduct(String filterType) {
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
            case "Minimum price":
                filteredProducts = products;
                for (int i = 0; i < filteredProducts.size() - 1; i++) {
                    int minIdx = i;
                    for (int j = i + 1; j < filteredProducts.size(); j++) {
                        if (filteredProducts.get(j).getPrice() < filteredProducts.get(minIdx).getPrice()) {
                            minIdx = j;
                        }
                    }

                    Product temp = filteredProducts.get(minIdx);
                    Product iProduct = filteredProducts.get(i);
                    filteredProducts.add(minIdx, iProduct);
                    filteredProducts.add(i, temp);
                }
            case "Maximum price":
                filteredProducts = products;
                for (int i = 0; i < filteredProducts.size() - 1; i++) {
                    int maxIdx = i;
                    for (int j = i + 1; j < filteredProducts.size(); j++) {
                        if (filteredProducts.get(j).getPrice() > filteredProducts.get(maxIdx).getPrice()) {
                            maxIdx = j;
                        }
                    }

                    Product temp = filteredProducts.get(maxIdx);
                    Product iProduct = filteredProducts.get(i);
                    filteredProducts.add(maxIdx, iProduct);
                    filteredProducts.add(i, temp);
                }
        }
        return filteredProducts;
    }
}
