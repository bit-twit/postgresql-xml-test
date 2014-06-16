package org.bittwit.postgresql.xml.test;

import java.util.List;

public interface UserDao {

    public void save (User u);

    public List<User> getByName (String name);

    /**
     * @param sex - male/female
     * @return
     */
    public List<User> getByPayloadPropertySex (String sex);

    public List<UserDto> getUserDtoByPayloadPropertySex (String sex);
}
