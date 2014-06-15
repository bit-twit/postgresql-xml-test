package org.bittwit.postgresql.xml.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value="userDao")
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User u) {
        getEntityManager().persist(u);
        System.out.println("Saved user : " + u);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getByName(String name) {
        System.out.println("Getting user with name:" + name);
        Query query = entityManager.createQuery("select u from User u");
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

//    TODO: implement a select with xpath for <user><sex>text value
//    public Stock findByStockCode(String stockCode){
//        List list = getHibernateTemplate().find(
//                      "from Stock where stockCode=?",stockCode
//                );
//        return (Stock)list.get(0);
//    }
}
