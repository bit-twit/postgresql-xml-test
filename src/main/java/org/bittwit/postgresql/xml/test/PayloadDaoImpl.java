package org.bittwit.postgresql.xml.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("payloadDao")
public class PayloadDaoImpl implements PayloadDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Payload p) {
        entityManager.persist(p);
        System.out.println("Saved payload : " + p);
    }

    @Override
    public Payload getByUserId(Long userId) {
        Payload p = entityManager.find(Payload.class, userId);
        System.out.println(p != null ? "Found payload with id: " + p.getPayload() : "No payload found for id: " + userId);
        return p;
    }

}
