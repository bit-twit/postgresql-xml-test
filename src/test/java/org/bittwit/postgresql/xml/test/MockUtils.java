package org.bittwit.postgresql.xml.test;

public class MockUtils {

    private static final String XML_PAYLOAD="<user><sex>male</sex></user>";

    public static User createNewUser () {
        return new User(null, "gogu", XML_PAYLOAD);
    }
}
