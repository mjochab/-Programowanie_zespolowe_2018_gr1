package pojo;


public class Doctor {

    private int doctorId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;
    private int specializationId;  //TODO: convert to foreign key
    private String password;

    private Address address;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String doctorToString() {
        return String.format("%s %s %s %s %s %s %s %s",
                "patientId: " + getDoctorId(),
                "firstName: " + getFirstName(),
                "lastName: " + getLastName(),
                "pesel: " + getPesel(),
                "email: " + getEmail(),
                "phone: " + getPhoneNumber(),
                "doctor: " + getSpecializationId(),
                "address: " + getAddress().toString()
        );
    }

}
