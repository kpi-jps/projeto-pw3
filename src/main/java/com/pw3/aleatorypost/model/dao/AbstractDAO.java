package com.pw3.aleatorypost.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDAO<E,PK extends Serializable>{
    private final Class<E> entityClass =
            (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void save(E entity) {
        entityManager.persist(entity);
    }

    public void update(E newEntity){
        entityManager.merge(newEntity);
    }

    public void delete(PK id){
        entityManager.remove(entityManager.find(entityClass,id));
    }

    public List<E> findAll(){
        return entityManager
                .createQuery("from "+entityClass.getSimpleName(),entityClass)
                .getResultList();
    }

    public E findById(PK id){
        return entityManager.find(entityClass,id);
    }

    protected List<E> createQuery(String jpql, Object... params){
        TypedQuery<E> query = entityManager.createQuery(jpql,entityClass);
        for ( int i= 0; i < params.length; i++){
            query.setParameter(i+1,params[i]);
        }
        return query.getResultList();
    }
}

