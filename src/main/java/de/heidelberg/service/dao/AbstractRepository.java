package de.heidelberg.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<E> {

    @PersistenceContext(unitName = "user-persistence-unit")
    protected EntityManager entityManager;

    private Class<E> entityClass;

    AbstractRepository()
    {
    }

    AbstractRepository(final Class<E> entityClass)
    {
        this.entityClass = entityClass;
    }

    public E persist(E entity)
    {
        entityManager.persist(entity);
        return entity;
    }

    public E findById(Long id)
    {
        return entityManager.find(entityClass, id);
    }
    public E findByUsername(String username)
    {
        return entityManager.find(entityClass, username);
    }

    public void remove(E entity)
    {
        entityManager.remove(entityManager.merge(entity));
    }

}
