package mappers;

import javafx.collections.ObservableList;
import org.apache.ibatis.annotations.Select;
import pojo.PatientAdministrationModel;


public interface PatientAdministrationMapper {


    ObservableList<PatientAdministrationModel> getAllPatients();
}
