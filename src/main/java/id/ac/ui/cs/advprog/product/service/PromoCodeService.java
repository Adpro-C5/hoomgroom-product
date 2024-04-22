package id.ac.ui.cs.advprog.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.product.model.PromoCode;
import id.ac.ui.cs.advprog.product.repository.ManageRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Service
@Qualifier("promoCodeService")
public class PromoCodeService implements ManageService<PromoCode> {
  
  @Autowired
  @Qualifier("promoCodeRepository")
  ManageRepository<PromoCode> repository;

  @Override
  public PromoCode findById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<PromoCode> findAll() {
    Iterator<PromoCode> productIterator = repository.findAll();
    List<PromoCode> products = new ArrayList<PromoCode>();
    productIterator.forEachRemaining(products::add);
    return products;
  }

  @Override
  public PromoCode delete(String id) throws NoSuchElementException {
    PromoCode foundProduct = this.findById(id);
    if (foundProduct != null) {
      return repository.deleteById(id);
    } else {   
      throw new NoSuchElementException("Promo Code doesn't exists");
    } 
  }

  @Override
  public PromoCode create(PromoCode promoCode) throws Exception {
    PromoCode foundPromoCode = this.findById(promoCode.getId().toString());
    if (!isNameUnique(promoCode.getName())) {
      throw new Exception("Name is already used");
    } else if (foundPromoCode == null) {
      repository.save(promoCode);
      return promoCode;
    } else {   
      throw new Exception("Promo Code already exists");
    } 
  }

  @Override
  public PromoCode edit(String id, PromoCode promoCode) throws NoSuchElementException {
    PromoCode foundPromoCode = this.findById(promoCode.getId().toString());
    if (!isNameUnique(promoCode.getName())) {
      throw new NoSuchElementException("Name is already used");
    } else if (foundPromoCode != null) {
      repository.save(promoCode);
      return promoCode;
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
