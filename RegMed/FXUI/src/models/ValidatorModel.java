package models;

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

    public static boolean containsOnlyNumbers(String numbers) {
        try {
            int number = Integer.parseInt(numbers);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean containsOnlyLetters(String letters) {
        return false;
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
}
