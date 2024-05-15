package id.ac.ui.cs.advprog.product.repository;

import java.util.UUID;
import java.util.Iterator;
import id.ac.ui.cs.advprog.product.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Repository
@Qualifier("productRepository")
public class ProductRepository implements ManageRepository<Product>, StatisticRepository{

  @Autowired
  EntityManager entityManager;

  @Override
  @Transactional
  public Product save(Product product) {
    if (findById(product.getId().toString()) == null) {
      entityManager.persist(product);
      return product;
    } else {
      entityManager.merge(product);
      return product;
    }
  }

  @Override
  @Transactional
  public Product findById(String id) {
    UUID uuid = UUID.fromString(id);
    return entityManager.find(Product.class, uuid);
  }

  @Override
  @Transactional
  public Product deleteById(String id) {
    Product product = findById(id);
    if (product != null) {
      entityManager.remove(product);
      return product;
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public Iterator<Product> findAll() {
    return entityManager.createQuery(
      "SELECT p FROM Product p", 
      Product.class
    ).getResultList().iterator();
  }

  @Override
  @Transactional
  public Iterator<Product> getBestTen() {
    return entityManager.createQuery(
      "SELECT p FROM Product p ORDER BY p.sales DESC LIMIT 10", 
      Product.class
    ).getResultList().iterator();
  }

  @Override
  @Transactional
  public Iterator<Product> getWorstTen() {
    return entityManager.createQuery(
      "SELECT p FROM Product p ORDER BY p.sales ASC LIMIT 10", 
      Product.class
    ).getResultList().iterator();
  }
}

