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
    public Product findProductDetail(UUID productID) {
        return null;
    }

    @Override
    public List<Product> showFilteredProduct(String filterType) {
        return null;
    }
}
