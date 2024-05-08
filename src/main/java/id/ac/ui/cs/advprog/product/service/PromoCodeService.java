package id.ac.ui.cs.advprog.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.product.model.PromoCode;
import id.ac.ui.cs.advprog.product.repository.ManageRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

@Service
@Qualifier("promoCodeService")
public class PromoCodeService implements ManageService<PromoCode> {
  
  @Autowired
  @Qualifier("promoCodeRepository")
  ManageRepository<PromoCode> repository;

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<PromoCode> findById(String id) {
    return CompletableFuture.completedFuture(repository.findById(id));
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<List<PromoCode>> findAll() {
    Iterator<PromoCode> productIterator = repository.findAll();
    List<PromoCode> products = new ArrayList<PromoCode>();
    productIterator.forEachRemaining(products::add);
    return CompletableFuture.completedFuture(products);
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<PromoCode> delete(String id) throws NoSuchElementException {
    PromoCode foundProduct = repository.findById(id);
    if (foundProduct != null) {
      return CompletableFuture.completedFuture(repository.deleteById(id));
    } else {   
      throw new NoSuchElementException("Promo Code doesn't exists");
    } 
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<PromoCode> create(PromoCode promoCode) throws Exception {
    PromoCode foundPromoCode = repository.findById(promoCode.getId().toString());
    if (!isNameUnique(promoCode.getName())) {
      throw new Exception("Name is already used");
    } else if (foundPromoCode == null) {
      repository.save(promoCode);
      return CompletableFuture.completedFuture(promoCode);
    } else {   
      throw new Exception("Promo Code already exists");
    } 
  }

  @Override
  @Async("asyncTaskExecutor")
  public CompletableFuture<PromoCode> edit(String id, PromoCode promoCode) throws NoSuchElementException {
    PromoCode foundPromoCode = repository.findById(promoCode.getId().toString());
    if (!isNameUnique(promoCode.getName())) {
      throw new NoSuchElementException("Name is already used");
    } else if (foundPromoCode != null) {
      repository.save(promoCode);
      return CompletableFuture.completedFuture(promoCode);
    } else {   
      throw new NoSuchElementException("Promo Code doesn't exists");
    } 
  }

  public boolean isNameUnique(String name) {
    Iterator<PromoCode> promos = repository.findAll();
    while (promos.hasNext()) {
      if (promos.next().getName().equals(name)) {
        return false;
      }
    }
    return true;
  }
}
