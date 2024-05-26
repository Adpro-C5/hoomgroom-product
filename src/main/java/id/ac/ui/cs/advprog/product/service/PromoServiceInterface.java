package id.ac.ui.cs.advprog.product.service;

import id.ac.ui.cs.advprog.product.model.PromoCode;
import java.util.concurrent.CompletableFuture;
public interface PromoServiceInterface extends ManageService<PromoCode>{
  CompletableFuture<PromoCode> findByName(String name);  
}
