package id.ac.ui.cs.advprog.product.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
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
  @Async("asyncTaskExecutor")
  public CompletableFuture<Product> findById(String id) {
    return CompletableFuture.completedFuture(repository.findById(id));
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<List<Product>> findAll() {
    Iterator<Product> productIterator = repository.findAll();
    List<Product> products = new ArrayList<Product>();
    productIterator.forEachRemaining(products::add);
    return CompletableFuture.completedFuture(products);
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<Product> delete(String id) throws NoSuchElementException {
    Product foundProduct = repository.findById(id);
    if (foundProduct != null) {
      return CompletableFuture.completedFuture(repository.deleteById(id));
    } else {   
      throw new NoSuchElementException("Product doesn't exists");
    } 
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<Product> create(Product product) throws Exception {
    Product foundProduct = repository.findById(product.getId().toString());
    if (foundProduct == null) {
      repository.save(product);
      return CompletableFuture.completedFuture(product);
    } else {   
      throw new Exception("Product already exists");
    } 
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<Product> edit(String id, Product product) throws NoSuchElementException {
    Product foundProduct = repository.findById(product.getId().toString());
    if (foundProduct != null) {
      repository.save(product);
      return CompletableFuture.completedFuture(product);
    } else {   
      throw new NoSuchElementException("Product doesn't exists");
    } 
  }
}
