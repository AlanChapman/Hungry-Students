package za.ac.cput.utils;

import java.util.UUID;

public class Helper {

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.toString().isEmpty() || obj == "";
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

//    public static boolean isValidEmail(String emailAddress) {
//        EmailValidator validator = EmailValidator.getInstance();
//
//        return validator.isValid(emailAddress);
//    }

}
