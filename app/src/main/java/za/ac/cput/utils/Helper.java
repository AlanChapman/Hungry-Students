package za.ac.cput.utils;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.UUID;

import za.ac.cput.MainActivity;
import za.ac.cput.R;
import za.ac.cput.domain.Objective;

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
