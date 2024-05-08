package id.ac.ui.cs.advprog.product.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.repository.ManageRepository;

@Service
public class StatisticServiceImpl implements StatsisticService {
  
  @Autowired
  @Qualifier("productRepository")
  ManageRepository<Product> repository;

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<List<Product>> findBestTen() {
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
      return CompletableFuture.completedFuture(result.subList(0, 10));
    } else {
      return CompletableFuture.completedFuture(result);
    }
  }
  
  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<List<Product>> findWorstTen() {
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
      return CompletableFuture.completedFuture(result.subList(0, 10));
    } else {
      return CompletableFuture.completedFuture(result);
    }
  }
}
