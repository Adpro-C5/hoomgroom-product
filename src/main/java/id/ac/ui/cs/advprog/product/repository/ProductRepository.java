package id.ac.ui.cs.advprog.product.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import id.ac.ui.cs.advprog.product.model.Product;

public class ProductRepository implements ManageRepository<Product>{
  private List<Product> productData = new ArrayList<Product>();

  public Product save(Product product) {
    int index = 0;
    for (Product savedProduct : productData) {
      if (savedProduct.getId().equals(product.getId())) {
        productData.remove(index);
        productData.add(index, product);
        return product;
      }

      index += 1;
    }

    productData.add(product);
    return product;
  }

  public Product findById(String id) {
    for (Product savedProduct : productData) {
      if (id.equals(savedProduct.getId().toString())) {
        return savedProduct;
      }
    }

    throw new NoSuchElementException();
  }

  public Product deleteById(String id) {
    int index = 0;
    for (Product savedProduct : productData) {
      if (id.equals(savedProduct.getId().toString())) {
        productData.remove(index);
        return savedProduct;
      }

      index += 1;
    }

    throw new NoSuchElementException();
  }

  public Iterator<Product> findAll() {
    return productData.iterator();
  }
}

