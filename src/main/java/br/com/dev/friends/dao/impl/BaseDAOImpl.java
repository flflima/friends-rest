package br.com.dev.friends.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.dev.friends.dao.BaseDAO;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> clazz;

	protected BaseDAOImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	@Transactional
	public void delete(T entity) {
		this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
	}

	@Override
	@Transactional
	public T save(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	@Override
	@Transactional
	public T update(T entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	public T findById(Object id) {
		return this.entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.clazz.getName()).getResultList();
	}

}
