package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * w miejscu date='2018-05-28' ->należy umieścić funkcję CURDATE();
 */
public interface PatientListMapper {
    @Select("select distinct s.id_admission_day, visit_hour, s.id_patient, p.first_name," +
            " p.last_name from singlevisits s join admissiondays a join doctorworkingdays " +
            "on a.id_doctor_working_day= doctorworkingdays.id_doctor_working_day JOIN patients p " +
            "on s.id_patient=p.id_patient where doctorworkingdays.id_doctor=#{doctorId} " +
            "and a.id_admission_day = s.id_admission_day and a.date=CURDATE() ;")
    @Results({
            @Result(property = "visitHour", column = "visit_hour"),
            @Result(property = "id", column = "id_patient"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")

    })
    List<PatientList> getPatientList(int doctorId);

    @Select("select first_name, last_name from patients where id_patient={#patientId}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    Patient getFirstLastName(int patientId);
}
