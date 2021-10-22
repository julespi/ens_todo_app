package com.julespi.ens_todo_app_back.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public abstract class GenericDao<T> {

    @PersistenceContext
    public EntityManager entityManager;

    public T save(T t){
        entityManager.persist(t);
        return t;
    }

    public T update(T t){
        entityManager.merge(t);
        return t;
    }

    public T findById(Class<T> clazz, Long id) throws RuntimeException{
        T t = entityManager.find(clazz, id);
        if(t == null){
            throw new RuntimeException("No Record Found with id "+id);
        }
        return t;
    }

    public void remove(T t){
        entityManager.remove(t);
    }

    public List<T> list(Class<T> clazz) throws RuntimeException{
        String query = "FROM "+clazz.getSimpleName();
        return new ArrayList<>(entityManager.createQuery(query,clazz).getResultList());
    }
}
