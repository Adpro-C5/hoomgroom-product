package id.ac.ui.cs.advprog.product.repository;
import java.util.Iterator;
import id.ac.ui.cs.advprog.product.model.Product;

public interface StatisticRepository {
  Iterator<Product> getBestTen();
  Iterator<Product> getWorstTen();
}
