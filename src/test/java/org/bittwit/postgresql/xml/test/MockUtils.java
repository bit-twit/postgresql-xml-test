package org.bittwit.postgresql.xml.test;

public class MockUtils {

    private static final String XML_MALE_PAYLOAD="<user><sex>male</sex></user>";
    private static final String XML_FEMALE_PAYLOAD="<user><sex>female</sex></user>";

    public static User createNewMaleUser () {
        User u = new User(null, "gogu");
        Payload p = new Payload(u, XML_MALE_PAYLOAD);
        u.setPayload(p);
        return u;
    }

    public static User createNewFemaleUser () {
        User u = new User(null, "ana");
        Payload p = new Payload(u, XML_FEMALE_PAYLOAD);
        u.setPayload(p);
        return u;
    }

}
