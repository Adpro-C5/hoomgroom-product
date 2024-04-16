package id.ac.ui.cs.advprog.product.service;
import java.util.List;

public interface ManageService<E> {
  public E findById(String id);
  public List<E> findAll();
  public E delete(String id);
  public E create(E e);
  public E edit(String id, E e);
}
