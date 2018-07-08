package br.com.dev.friends.dao;

import java.util.List;

public interface BaseDAO<T> {

	T save(T entity);

	T update(T entity);

	void delete(T entity);

	T findById(Object id);

	List<T> findAll();
}
