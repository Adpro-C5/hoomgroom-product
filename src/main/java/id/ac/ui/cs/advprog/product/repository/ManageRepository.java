package id.ac.ui.cs.advprog.product.repository;
import java.util.Iterator;

public interface ManageRepository<E> {
  E save(E e);
  E findById(String id);
  E deleteById(String id);
  Iterator<E> findAll();
}
