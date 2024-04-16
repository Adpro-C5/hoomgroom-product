package id.ac.ui.cs.advprog.product.service;
import java.util.List;
import java.util.NoSuchElementException;

public interface ManageService<E> {
  public E findById(String id);
  public List<E> findAll();
  public E delete(String id) throws NoSuchElementException;
  public E create(E e) throws Exception;
  public E edit(String id, E e) throws NoSuchElementException;
}
