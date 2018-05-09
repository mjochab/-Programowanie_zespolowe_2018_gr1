package pojo;

public class Administrator extends User{


    public String administratorToString() {
        return String.format("%s %s %s %s %s %s",
                "adminId: " + getId(),
                "firstName: " + getFirstName(),
                "lastName: " + getLastName(),
                "pesel: " + getPesel(),
                "email: " + getEmail(),
                "phone: " + getPhoneNumber()
        );
    }
}
