public class ToHtmlPatient {
    private String firstName, lastName, pesel, zipCode, city, street, number, phoneNumber;

    public ToHtmlPatient(String firstName, String lastName, String pesel, String zipCode, String city, String street, String number, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.phoneNumber = phoneNumber;
    }

    public ToHtmlPatient(String firstName, String lastName, String pesel, String zipCode, String city, String street, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
