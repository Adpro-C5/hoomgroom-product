package id.ac.ui.cs.advprog.product.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.repository.ManageRepository;

@Service
@Qualifier("productService")
public class ProductService implements ManageService<Product>{
  @Autowired
  @Qualifier("productRepository")
  private ManageRepository<Product> repository; 

  @Override
  public Product findById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Product> findAll() {
    Iterator<Product> productIterator = repository.findAll();
    List<Product> products = new ArrayList<Product>();
    productIterator.forEachRemaining(products::add);
    return products;
  }

  @Override
  public Product delete(String id) throws NoSuchElementException {
    Product foundProduct = this.findById(id);
    if (foundProduct != null) {
      return repository.deleteById(id);
    } else {   
      throw new NoSuchElementException("Product doesn't exists");
    } 
  }

  @Override
  public Product create(Product product) throws Exception {
    Product foundProduct = this.findById(product.getId().toString());
    if (foundProduct == null) {
      repository.save(product);
      return product;
    } else {   
      throw new Exception("Product already exists");
    } 
  }

  @Override
  public Product edit(String id, Product product) throws NoSuchElementException {
    Product foundProduct = this.findById(product.getId().toString());
    if (foundProduct != null) {
      repository.save(product);
      return product;
    } else {   
      throw new NoSuchElementException("Product doesn't exists");
    } 
  }
}
