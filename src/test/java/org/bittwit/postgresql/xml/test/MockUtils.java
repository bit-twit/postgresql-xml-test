package org.bittwit.postgresql.xml.test;

public class MockUtils {

    private static final String XML_MALE_PAYLOAD="<user><sex>male</sex></user>";
    private static final String XML_FEMALE_PAYLOAD="<user><sex>female</sex></user>";

    public static User createNewMaleUser () {
        return new User(null, "gogu", XML_MALE_PAYLOAD);
    }

    public static User createNewFemaleUser () {
        return new User(null, "ana", XML_FEMALE_PAYLOAD);
    }

}
