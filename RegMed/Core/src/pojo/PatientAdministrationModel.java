package pojo;

public class PatientAdministrationModel {
    private String patientId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phone;
    private String firstContactDoctorFirstName;
    private String firstContactDoctorLastName;
    private String zipCode;
    private String city;
    private String street;
    private String number;

    public PatientAdministrationModel(String patientId, String firstName, String lastName,
                                      String pesel, String email, String phone, String firstContactDoctorFirstName,
                                      String firstContactDoctorLastName, String zipCode, String city, String street,
                                      String number) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.email = email;
        this.phone = phone;
        this.firstContactDoctorFirstName = firstContactDoctorFirstName;
        this.firstContactDoctorLastName = firstContactDoctorLastName;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.number = number;
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstContactDoctorFirstName() {
        return firstContactDoctorFirstName;
    }

    public void setFirstContactDoctorFirstName(String firstContactDoctorFirstName) {
        this.firstContactDoctorFirstName = firstContactDoctorFirstName;
    }

    public String getFirstContactDoctorLastName() {
        return firstContactDoctorLastName;
    }

    public void setFirstContactDoctorLastName(String firstContactDoctorLastName) {
        this.firstContactDoctorLastName = firstContactDoctorLastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
