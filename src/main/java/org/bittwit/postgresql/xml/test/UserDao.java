package org.bittwit.postgresql.xml.test;

import java.util.List;

import javax.persistence.EntityManager;

public interface UserDao {
    public EntityManager getEntityManager();

    public void save (User u);

    public List<User> getByName (String name);

    /**
     * @param sex - male/female
     * @return
     */
    public List<User> getByPayloadPropertySex (String sex);

    public List<UserDto> getUserDtoByPayloadPropertySex (String sex);

    public List<User> getByPartnerId (Long partnerId);

    User getById(Long userId);
}
