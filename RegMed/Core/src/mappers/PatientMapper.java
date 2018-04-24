package mappers;

import pojo.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper {

    @Select("SELECT * FROM PATIENTS WHERE id_patient = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id_patient"),
            @Result(property = "globalId", column = "id_global"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "addressId", column = "id_address"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "firstContactDoctorId", column = "id_firstcontact_doctor"),
            @Result(property = "password", column = "password")
    })
    Patient get(int id);



}
