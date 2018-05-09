package dto;

import exceptions.ValidationException;
import pojo.Address;
import pojo.Doctor;
import pojo.Patient;
import pojo.User;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdministrationValidators {


    
    public static boolean userValidation(User user) {
        if (
                !nameCheck(user.getFirstName()) ||
                        !nameCheck(user.getLastName()) ||
                        !peselCheck(user.getPesel()) ||
                        !emailCheck(user.getEmail()) ||
                        !phoneCheck(user.getPhoneNumber())
                ) {
            return false;
        } else
            return true;
    }


    public static String userValidationErrors(User user) throws ValidationException {
        String errors = "";

        if (!nameCheck(user.getFirstName())) {
            errors += "First name - wrong name format: " + user.getFirstName() + "\n";
        }

        if (!nameCheck(user.getLastName())) {
            errors += "Last name - wrong name format.\n";
        }

        if (!peselCheck(user.getPesel())) {
            errors += String.format("Pesel must have 11 digits: %s (%s)\n", user.getPesel(),
                    user.getPesel().length());
        }

        if (!emailCheck(user.getEmail())) {
            errors += "Wrong email format: " + user.getEmail() + "\n";
        }

        if (!phoneCheck(user.getPhoneNumber())) {
            errors += String.format("Phone number must have 9 digits: %s (%s)\n", user.getPhoneNumber(),
                    user.getPhoneNumber().length());
        }

        return errors;
    }


    public static boolean addressValidation(Address address) {
        if (
                !cityCheck(address.getCity()) ||
                !zipCheck(address.getZip()) ||
                !streetCheck(address.getStreet())
                ) {
            return false;
        } else
            return true;
    }

    public static String addressValidationErrors(Address address) {
        String errors = "";

        if (!cityCheck(address.getCity())) {
            errors += String.format("City name - wrong format: %s \n", address.getCity());
        }

        if (!zipCheck(address.getZip())) {
            errors += String.format("Zip - wrong format: %s, required (xx-xxx)\n", address.getZip());
        }

        if (!streetCheck(address.getStreet())) {
            errors += String.format("Street name - wrong format: %s \n", address.getStreet());
        }

        return errors;
    }



    private static boolean nameCheck(String text) {
        return text.matches("[A-Z][a-z]+");   //Big letter, several small letters
    }

    private static boolean peselCheck(String text) {
        return text.matches("\\d{11}"); //TODO: sex digit etc. rules
    }

    private static boolean emailCheck(String text) {
        Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = emailRegex.matcher(text);
        return matcher.matches();
    }

    private static boolean phoneCheck(String text) {
        return text.matches("\\d{9}");
    }

    private static boolean cityCheck(String text) {
        return text.matches("[A-Z][a-z]+");
    }

    private static boolean streetCheck(String text) {
        return text.matches("[A-Z][a-z]+");
    }

    private static boolean zipCheck(String text) {
        return text.matches("\\d{2}-\\d{3}");
    }








}
