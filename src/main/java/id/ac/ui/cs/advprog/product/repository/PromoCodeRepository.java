package id.ac.ui.cs.advprog.product.repository;
import java.util.UUID;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.product.model.PromoCode;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Qualifier("promoCodeRepository")
public class PromoCodeRepository implements PromoCodeRepositoryInterface {
  
  @Autowired
  EntityManager entityManager;

  @Override
  @Transactional
  public PromoCode save(PromoCode promoCode) {
    if (findById(promoCode.getId().toString()) == null) {
      entityManager.persist(promoCode);
      return promoCode;
    } else {
      entityManager.merge(promoCode);
      return promoCode;
    }    
  }

  @Override
  @Transactional
  public PromoCode findById(String id) {
    UUID uuid = UUID.fromString(id);
    return entityManager.find(PromoCode.class, uuid);
  }

  @Override
  @Transactional
  public PromoCode deleteById(String id) {
    PromoCode promoCode = findById(id);
    if (promoCode != null) {
      entityManager.remove(promoCode);
      return promoCode;
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public Iterator<PromoCode> findAll() {
    return entityManager.createQuery(
      "SELECT p FROM PromoCode p", 
      PromoCode.class
    ).getResultList().iterator();
  }

  @Override
  @Transactional
  public PromoCode findByName(String name) {
    return entityManager.createQuery(
      "SELECT p FROM PromoCode p WHERE p.name = :name", 
      PromoCode.class
    ).setParameter("name", name).getSingleResult();
  }
}
