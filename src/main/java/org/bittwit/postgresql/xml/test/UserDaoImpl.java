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
        Query query = entityManager.createQuery("select u from User u where u.name LIKE '" + name + "'");
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getByPayloadPropertySex(String sex) {
        System.out.println("Getting user with sex:" + sex);
        Query query = entityManager.createNativeQuery("SELECT id, name, payload FROM user_test_table" +
                " WHERE (xpath('/user/sex/text()', payload))[1]\\:\\:text LIKE '" + sex + "'", User.class);
        List<User> users = (List<User>) query.getResultList();
        return users;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getUserDtoByPayloadPropertySex(String sex) {
        System.out.println("Getting user with sex:" + sex);
        Query query = entityManager.createNativeQuery("SELECT name, (xpath('/user/sex/text()', payload))[1]\\:\\:text sex FROM user_test_table" +
                " WHERE (xpath('/user/sex/text()', payload))[1]\\:\\:text LIKE '" + sex + "'", "GetUserDtoByPayloadPropertySexQuery");
        List<UserDto> users = (List<UserDto>) query.getResultList();
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
