package htmlParser;

public class ToHtmlDoctor {
    private String firstName,
            lastName,
            specialization,
            phoneNumber;

    public ToHtmlDoctor(String firstName, String lastName, String specialization, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public ToHtmlDoctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
