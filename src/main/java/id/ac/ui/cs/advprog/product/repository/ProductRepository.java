package id.ac.ui.cs.advprog.product.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import id.ac.ui.cs.advprog.product.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Qualifier;

@Repository
@Qualifier("productRepository")
public class ProductRepository implements ManageRepository<Product>{
  private List<Product> productData = new ArrayList<Product>();

  @Override
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

  @Override
  public Product findById(String id) {
    for (Product savedProduct : productData) {
      if (id.equals(savedProduct.getId().toString())) {
        return savedProduct;
      }
    }

    return null;
  }

  @Override
  public Product deleteById(String id) {
    int index = 0;
    for (Product savedProduct : productData) {
      if (id.equals(savedProduct.getId().toString())) {
        productData.remove(index);
        return savedProduct;
      }

      index += 1;
    }

    return null;
  }

  @Override
  public Iterator<Product> findAll() {
    return productData.iterator();
  }
}

