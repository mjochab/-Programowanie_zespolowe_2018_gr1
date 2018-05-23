package mappers;


import org.apache.ibatis.annotations.*;
import pojo.PatientData;

import java.util.List;

@Mapper
public interface PatientDataMapper {

    @Select("select id_patient, first_name , last_name from patients")
    @Results({
            @Result(property = "patientId", column = "id_patient"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    List<PatientData> getAllPatientDataToTable();

}
