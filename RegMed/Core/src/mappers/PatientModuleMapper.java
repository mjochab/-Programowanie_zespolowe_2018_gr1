package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.*;

import java.util.List;

@Mapper
public interface PatientModuleMapper {

    @Select("SELECT id_patient, first_name, last_name, PESEL, id_address," +
            " email, phone_number,id_firstcontact_doctor FROM patients " +
            "WHERE id_patient = #{patientId}")
    @Results({
            @Result(property = "id", column = "id_patient"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER)),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            //@Result(property = "firstContactDoctorId", column = "id_firstcontact_doctor")   //TODO: update db foreign key
            @Result(property = "firstContactDoctor", column = "id_firstcontact_doctor",
                    javaType = Doctor.class, one = @One(select = "selectFirstcontactDoctor",
                    fetchType = FetchType.EAGER))
    })
    Patient getPatient(int patientId);


    @Insert("Insert into singlevisits(id_single_visit, id_admission_day, visit_hour, id_patient) " +
            "values (#{id}, #{admissionDay.id}, #{visitHour}, #{patient.id})")
    void insertIntoSingleVisits(SingleVisit singleVisit);

    @Insert("Insert into admissiondays(id_admission_day, id_doctor_working_day, date) " +
            "values (#{id}, #{doctorWorkingDay.id}, #{date})")
    void insertIntoAdmissionDays(AdmissionDay admissionDay);

    @Insert("INSERT into doctorworkingdays(id_doctor_working_day, id_doctor, day, hour_from, hour_to, hour_interval) " +
            "VALUES (#{doctorWorkingDayId}, #{doctorId}, #{day}, #{hourFrom}, #{hourTo}, #{hourInterval})")
    void insertIntoDoctorWorkingDays(DoctorWorkingDays doctorWorkingDay);



    @Select("Select id_doctor, first_name, last_name, phone_number, id_specialization " +
            "from doctors where id_doctor=#{doctorId}")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "specializationId", column = "id_specialization")
    })
    Doctor selectFirstcontactDoctor(int doctorId);


    @Select("SELECT id_address, city, zip_code, street, number from addresses " +
            "where id_address=#{addressId}")
    @Results(value = {
            @Result(property = "addressId", column = "id_address"),
            @Result(property = "city", column = "city"),
            @Result(property = "zip", column = "zip_code"),
            @Result(property = "street", column = "street"),
            @Result(property = "number", column = "number")
    })
    Address selectAddress(int addressId);

    @Select("select day, hour_from, hour_to, hour_interval from doctorworkingdays where id_doctor=#{doctorId}")
    @Results({
            @Result(property = "doctorId", column = "id_doctor"),
            @Result(property = "day", column = "day"),
            @Result(property = "hourFrom", column = "hour_from"),
            @Result(property = "hourTo", column = "hour_to"),
            @Result(property = "hourInterval", column = "hour_interval")
    })
    List<DoctorWorkingDays> getDoctorWorkingDays(int doctorId);

    @Select("select id_doctor, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_specialization from doctors")
    @Results({
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER)),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "specializationId", column = "id_specialization")
    })
    List<Doctor> getAllDoctors();

}
