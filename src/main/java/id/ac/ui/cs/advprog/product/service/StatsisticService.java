package id.ac.ui.cs.advprog.product.service;

import java.util.List;

import id.ac.ui.cs.advprog.product.model.Product;

public interface StatsisticService {
  public List<Product> findBestTen();
  public List<Product> findWorstTen();
}
