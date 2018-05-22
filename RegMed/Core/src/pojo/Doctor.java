package pojo;


public class Doctor extends User{

    private int specializationId;  //TODO: convert to foreign key
    private Address address;



    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String doctorToString() {
        return String.format("%s %s %s %s %s %s %s %s",
                "patientId: " + getId(),
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