package ua.kpi.data.dao;


import java.util.List;

public interface GenericDao<T, K> {

     T save(T object) throws PersistException;

     T getById(K key) throws PersistException;

     void update(T item) throws PersistException;

     void delete(T item) throws PersistException;

     void deleteAll() throws PersistException;

     List<T> getAll() throws PersistException;

}
