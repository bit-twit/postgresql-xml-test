package org.bittwit.postgresql.xml.test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    UserDao dao;

    @Test
    public void testSave () {
        User  u = MockUtils.createNewMaleUser();
        dao.save(u);
        List<User> persistedUsers = dao.getByName(u.getName());
        
        Assert.assertTrue(persistedUsers.size() > 0);
        Assert.assertNotNull(persistedUsers.get(0).getId());
    }

    @Test
    public void testGetByName () {
        User  u = MockUtils.createNewMaleUser();

        dao.save(u);
        u = MockUtils.createNewMaleUser();
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        dao.save(u);

        u = MockUtils.createNewFemaleUser();
        dao.save(u);
        u = MockUtils.createNewFemaleUser();
        dao.save(u);

        List<User> persistedUsers = dao.getByName(u.getName());

        Assert.assertEquals(2, persistedUsers.size());
    }

    @Test
    public void testGetByPayloadPropertySex () {
        User  u = MockUtils.createNewMaleUser();
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        dao.save(u);

        u = MockUtils.createNewFemaleUser();
        dao.save(u);
        u = MockUtils.createNewFemaleUser();
        dao.save(u);

        List<User> persistedUsers = dao.getByPayloadPropertySex("male");

        Assert.assertEquals(3, persistedUsers.size());
    }

    @Test
    public void testGetUserDtoByPayloadPropertySex () {
        User  u = MockUtils.createNewMaleUser();
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        dao.save(u);

        u = MockUtils.createNewFemaleUser();
        dao.save(u);
        u = MockUtils.createNewFemaleUser();
        dao.save(u);

        List<UserDto> persistedUsers = dao.getUserDtoByPayloadPropertySex("male");

        Assert.assertEquals(3, persistedUsers.size());
        Assert.assertNotNull(persistedUsers.get(0).getName());
        Assert.assertNotNull(persistedUsers.get(1).getName());
        Assert.assertNotNull(persistedUsers.get(2).getName());
    }

    @Test
//    @Rollback(false)
    public void testGetByPartnerId () {
        Set<Long> partnerIds = new TreeSet<Long>();
        partnerIds.add(1L);
        partnerIds.add(2L);
        
        User u = MockUtils.createNewMaleUser();
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        u.setPartnerIds(partnerIds);
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        u.setPartnerIds(partnerIds);
        dao.save(u);
        u = MockUtils.createNewMaleUser();
        partnerIds = new TreeSet<Long>();
        partnerIds.add(1L);
        partnerIds.add(2L);
        partnerIds.add(3L);
        u.setPartnerIds(partnerIds);
        dao.save(u);

        List<User> users = dao.getByPartnerId(1L);
        Assert.assertEquals(3, users.size());

        users = dao.getByPartnerId(3L);
        Assert.assertEquals(1, users.size());
    }
}
