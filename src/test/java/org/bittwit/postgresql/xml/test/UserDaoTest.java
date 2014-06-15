package org.bittwit.postgresql.xml.test;

import java.util.List;

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
    public void testAccessDB () {
        User  u = MockUtils.createNewUser();
        dao.save(u);
        List<User> persistedUsers = dao.getByName(u.getName());
        
        Assert.assertTrue(persistedUsers.size() > 0);
    }
}
