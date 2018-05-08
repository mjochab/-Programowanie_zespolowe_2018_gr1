package pojo;

public class Patient extends User {

    private int firstContactDoctorId;

    private Address address;






    public int getFirstContactDoctorId() {
        return firstContactDoctorId;
    }

    public void setFirstContactDoctorId(int firstContactDoctorId) {
        this.firstContactDoctorId = firstContactDoctorId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String patientToString() {
        return String.format("%s %s %s %s %s %s %s %s",
                "patientId: " + getId(),
                "firstName: " + getFirstName(),
                "lastName: " + getLastName(),
                "pesel: " + getPesel(),
                "email: " + getEmail(),
                "phone: " + getPhoneNumber(),
                "doctor: " + getFirstContactDoctorId(),
                "address: " + getAddress().toString()
        );
    }
}
