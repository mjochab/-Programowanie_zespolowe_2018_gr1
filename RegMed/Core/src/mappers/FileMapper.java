package mappers;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Doctor;
import pojo.File;
import pojo.Patient;

import java.util.List;

public interface FileMapper {
    @Select("select id_patient, id_doctor, date, history from files where id_patient=#{patientId}")
    @Results({
            @Result(property = "patient", column = "id_patient", javaType = Patient.class,
                    one = @One(select = "getPatient", fetchType = FetchType.EAGER)),
            @Result(property = "doctor", column = "id_doctor", javaType = Doctor.class,
                    one = @One(select = "getDoctor", fetchType = FetchType.EAGER)),
            @Result(property = "date", column = "date"),
            @Result(property = "history", column = "history")
    })
    List<File> getPatientFiles(int patientId);

    @Select("SELECT id_patient, first_name, last_name, PESEL, id_address," +
            " email, phone_number,id_firstcontact_doctor FROM patients " +
            "WHERE id_patient = #{patientId}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER)),
            @Result(property = "phoneNumber", column = "phone_number")
    })
    Patient getPatient(int patientId);

    @Select("select first_name, last_name from doctors where id_doctor=#{doctorId}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
    })
    Doctor getDoctor(int doctorId);

    @Select("SELECT id_address, city, zip_code, street, number from addresses " +
            "where id_address=#{addressId}")
    @Results(value = {
            @Result(property = "city", column = "city"),
            @Result(property = "zip", column = "zip_code"),
            @Result(property = "street", column = "street"),
            @Result(property = "number", column = "number")
    })
    Address selectAddress(int addressId);
}
