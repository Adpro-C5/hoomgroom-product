package id.ac.ui.cs.advprog.product.service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

public interface ManageService<E> {
  public CompletableFuture<E> findById(String id);
  public CompletableFuture<List<E>> findAll();
  public CompletableFuture<E> delete(String id) throws NoSuchElementException;
  public CompletableFuture<E> create(E e) throws Exception;
  public CompletableFuture<E> edit(String id, E e) throws NoSuchElementException;
}
