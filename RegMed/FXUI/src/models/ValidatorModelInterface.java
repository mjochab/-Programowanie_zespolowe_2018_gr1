package models;

public interface ValidatorModelInterface {
    boolean isTime(String time);
    boolean containsOnlyNumbers(String numbers);
    boolean containsOnlyLetters(String letters);
}
