package pojo;


public class Doctor extends User{

    private int specializationId;  //TODO: convert to foreign key
    private Address address;
    private Specialization specialization;



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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }


//    public String toString() {
//        return String.format("%s %s %s", getFirstName(), getLastName(), getSpecialization().getName());
//    }

}