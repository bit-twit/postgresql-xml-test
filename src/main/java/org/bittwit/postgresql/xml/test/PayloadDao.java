package org.bittwit.postgresql.xml.test;

import javax.persistence.EntityManager;

public interface PayloadDao {
    public EntityManager getEntityManager();

    public void save (Payload p);

    public Payload getByUserId(Long userId);
}
