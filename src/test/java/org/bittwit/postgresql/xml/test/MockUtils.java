package org.bittwit.postgresql.xml.test;

import java.util.Random;

public class MockUtils {

    private static final String XML_MALE_PAYLOAD="<user><sex>male</sex></user>";
    private static final String XML_FEMALE_PAYLOAD="<user><sex>female</sex></user>";

    private static Random r = new Random();

    public static User createNewMaleUser () {
        User u = new User("gogu", r.nextLong(), r.nextLong());
        return u;
    }

    public static User createNewFemaleUser () {
        User u = new User("ana", r.nextLong(), r.nextLong());
        return u;
    }

    public static Payload createNewMalePayload () {
        return new Payload(XML_MALE_PAYLOAD);
    }

    public static Payload createNewFemalePayload () {
        return new Payload(XML_FEMALE_PAYLOAD);
    }
}
