package pojo;

public class Administrator {
    private int adminId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;
    private String password;



    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String administratorToString() {
        return String.format("%s %s %s %s %s %s",
                "adminId: " + getAdminId(),
                "firstName: " + getFirstName(),
                "lastName: " + getLastName(),
                "pesel: " + getPesel(),
                "email: " + getEmail(),
                "phone: " + getPhoneNumber()
        );
    }
}
