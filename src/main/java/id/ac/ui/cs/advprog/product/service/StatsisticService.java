package id.ac.ui.cs.advprog.product.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.product.model.Product;

public interface StatsisticService {
  public CompletableFuture<List<Product>> findBestTen();
  public CompletableFuture<List<Product>> findWorstTen();
}
