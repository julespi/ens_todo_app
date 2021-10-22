package com.julespi.ens_todo_app_back.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public abstract class GenericDao<T> {

    @PersistenceContext
    public EntityManager entityManager;

    public T save(T t){
        entityManager.persist(t);
        return t;
    }
}
