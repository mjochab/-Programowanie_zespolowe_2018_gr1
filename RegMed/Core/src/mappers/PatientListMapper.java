package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.*;

import java.util.List;

/**
 * w miejscu date='2018-05-28' ->należy umieścić funkcję CURDATE();
 */
public interface PatientListMapper {
    @Select("select s.id_admission_day, visit_hour, s.id_patient, p.first_name, p.last_name from singlevisits s inner join admissiondays as a JOIN patients p on s.id_patient=p.id_patient where a.id_admission_day = s.id_admission_day and date=CURDATE()")
    @Results({
            @Result(property = "visitHour", column = "visit_hour"),
            @Result(property = "id", column = "id_patient"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")

    })
    PatientList getPatientList(int doctorId);

    @Select("select first_name, last_name from patients where id_patient={#patientId}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    Patient getFirstLastName(int patientId);
}