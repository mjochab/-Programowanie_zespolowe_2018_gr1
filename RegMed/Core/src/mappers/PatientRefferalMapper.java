package mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import pojo.PatientsRefferal;


public interface PatientRefferalMapper {
    @Insert("INSERT into refferals ( id_refferal ,id_patient ,id_doctor ,id_specialist ,content) VALUES (#{refferalId},#{patientId},#{doctorId},#{specialistId},#{refferalforpatient})")
    @Options (useGeneratedKeys = true, keyProperty = "refferalId", keyColumn = "id_refferal")
    void addPatientsRefferal(PatientsRefferal patientsRefferal);
}
