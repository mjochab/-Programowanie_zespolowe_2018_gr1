package controllers;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PatientData {
    private SimpleIntegerProperty id ;
    private SimpleStringProperty firstName ;
    private SimpleStringProperty lastName ;

public PatientData(int id, String firstName, String lastName){
    this.id = new SimpleIntegerProperty(id);
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);

}

    public int getId() {
        return id.get();
    }



    public String getFirstName() {
        return firstName.get();
    }



    public String getLastName() {
        return lastName.get();
    }


}
