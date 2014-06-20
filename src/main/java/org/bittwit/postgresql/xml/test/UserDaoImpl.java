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
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE '" + name + "'");
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getByPayloadPropertySex(String sex) {
        System.out.println("Getting user with sex:" + sex);
        Query query = entityManager.createNativeQuery("SELECT * FROM user_test_table u" +
                " INNER JOIN user_payload p ON u.user_id = p.user_id" +
                " WHERE (xpath('/user/sex/text()', p.payload))[1]\\:\\:text LIKE '" + sex + "'", User.class);
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getUserDtoByPayloadPropertySex(String sex) {
        System.out.println("Getting user with sex:" + sex);
        Query query = entityManager.createNativeQuery("SELECT name, (xpath('/user/sex/text()', p.payload))[1]\\:\\:text sex FROM user_test_table u" +
        		" INNER JOIN user_payload p ON u.user_id = p.user_id" +
                " WHERE (xpath('/user/sex/text()', p.payload))[1]\\:\\:text LIKE '" + sex + "'", "GetUserDtoByPayloadPropertySexQuery");
        List<UserDto> users = (List<UserDto>) query.getResultList();
        return users;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getByPartnerId(Long partnerId) {
        System.out.println("Getting users with partnerId:" + partnerId);
        
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.partnerId = :partnerId");
        query.setParameter("partnerId", partnerId);
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    @Override
    public User getById(Long userId) {
        System.out.println("Getting user with id:" + userId);
        
        Query query = entityManager.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.payload p WHERE u.userId = :userId");
        query.setParameter("userId", userId);
        User result = (User) query.getSingleResult();
        result.getPayload();
        return result;
    }

//    TODO: implement a select with xpath for <user><sex>text value
//    public Stock findByStockCode(String stockCode){
//        List list = getHibernateTemplate().find(
//                      "from Stock where stockCode=?",stockCode
//                );
//        return (Stock)list.get(0);
//    }
}
