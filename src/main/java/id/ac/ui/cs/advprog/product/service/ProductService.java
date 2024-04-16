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

  public List<Product> findBestTen() {
    List<Product> result = new ArrayList<Product>();
    Iterator<Product> allProducts = repository.findAll();
    
    int index = 0;
    int index2 = 0;
    Product nextProduct = null;
    while (allProducts.hasNext()) {
      nextProduct = allProducts.next();
      if (index == 1) {
        result.add(nextProduct);
      } else if (index < 10) {
        index2 = 0;
        for (Product changedProduct:result) {
          if (changedProduct.getSales() < nextProduct.getSales()) {
            result.add(index2, nextProduct);
            break;
          }
          index2 += 1;
        } 
        if (index2 == result.size()) {
          result.addLast(nextProduct);
        }
      } else if (nextProduct.getSales() > result.get(9).getSales()) {
        index2 = 0;
        for (Product changedProduct:result) {
          if (changedProduct.getSales() < nextProduct.getSales()) {
            result.add(index2, nextProduct);
            break;
          }
          index2 += 1;
        } 
      }
      index += 1;
    }
    if (result.size() > 10) {
      return result.subList(0, 10);
    } else {
      return result;
    }
  }

  public List<Product> findWorstTen() {
    List<Product> result = new ArrayList<Product>();
    Iterator<Product> allProducts = repository.findAll();
    
    int index = 0;
    int index2 = 0;
    Product nextProduct = null;
    while (allProducts.hasNext()) {
      nextProduct = allProducts.next();
      if (index == 1) {
        result.add(nextProduct);
      } else if (index < 10) {
        index2 = 0;
        for (Product changedProduct:result) {
          if (changedProduct.getSales() > nextProduct.getSales()) {
            result.add(index2, nextProduct);
            break;
          }
          index2 += 1;
        } 
        if (index2 == result.size()) {
          result.addLast(nextProduct);
        }
      } else if (nextProduct.getSales() < result.get(9).getSales()) {
        index2 = 0;
        for (Product changedProduct:result) {
          if (changedProduct.getSales() > nextProduct.getSales()) {
            result.add(index2, nextProduct);
            break;
          }
          index2 += 1;
        } 
      }
      index += 1;
    }
    if (result.size() > 10) {
      return result.subList(0, 10);
    } else {
      return result;
    }
  }
}
