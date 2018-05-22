package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorModel {

    public static boolean isTime(String time) {
        if (Pattern.matches("\\d\\d:\\d\\d", time) ||
                Pattern.matches("\\d:\\d\\d", time)) {

            String hours[] = time.split(":");
            int hour = Integer.parseInt(hours[0]);
            int minute = Integer.parseInt(hours[1]);

            if (hour < 24 && minute < 60) {
                return true;
            } else return false;
        } else return false;
    }

    public static Boolean emailValidator(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean containsOnlyNumbers(String numbers) {
        try {
            int number = Integer.parseInt(numbers);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean containsOnlyLetters(String letters) {
        return letters.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]");
    }

    public static Boolean streetValidator(String text){
        return text.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*[0-9][0-9]");
    }

    public static Boolean peselValidator(String text) {
        if (text.length() == 11 && text.matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean intervalValidation(String minutes) {
        if (containsOnlyNumbers(minutes)) {
            if (Integer.parseInt(minutes) > 0 && Integer.parseInt(minutes) < 61) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean doctorEditDayValidator(Boolean isActive, String from, String to){
        if(isActive){
            if(isTime(from) && isTime(to)){
                return true;
            }

        }
        return false;
    }

    public static boolean postalCodeValidator (String code){
        return code.matches("[0-9][0-9]-[0-9][0-9][0-9]");
    }
}
