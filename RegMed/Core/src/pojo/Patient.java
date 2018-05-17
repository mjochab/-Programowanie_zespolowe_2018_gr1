package pojo;

/**
 * Plain Old Java Object class, using inherited user class and adding specially fields for patient.
 *
 * @see pojo.User
 * @author Szymon P
 *
 */
public class Patient extends User {

    private int firstContactDoctorId;

    /**
     * Address must be stored as object, not as integer id.
     * It is necesarry for EagerLoading address data for patient using mapper.
     *
     * @see mappers.PatientAdministrationMapper
     */
    private Address address;

    /**
     * Address must be stored as object, not as integer id.
     * It is necessary for EagerLoading firstContactDoctor data for patient using mapper.
     *
     * @see mappers.PatientAdministrationMapper
     */
    private Doctor firstContactDoctor;




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

    public Doctor getFirstContactDoctor() {
        return firstContactDoctor;
    }

    public void setFirstContactDoctor(Doctor firstContactDoctor) {
        this.firstContactDoctor = firstContactDoctor;
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
