package id.ac.ui.cs.advprog.product.repository;

import id.ac.ui.cs.advprog.product.model.PromoCode;

public interface NameFinderRepository {
  PromoCode findByName(String name);
}
