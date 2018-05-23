package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.*;

import java.util.List;

/**
 * Mapping data from database to java classes and vice versa.
 * It is using MyBatis framework.
 *
 * @author Paweł Lawera
 */
@Mapper
public interface PatientModuleMapper {

    /**
     * Getting patient specified by id.
     *
     * @param patientId patient which you want to get from database.
     * @return          patient from database.
     */
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


    /**
     * Inserting single visit to database.
     * It contains references to doctor and patient.
     *
     * @param singleVisit   data to insert.
     */
    @Insert("Insert into singlevisits(id_single_visit, id_admission_day, visit_hour, id_patient) " +
            "values (#{id}, #{admissionDay.id}, #{visitHour}, #{patient.id})")
    void insertIntoSingleVisits(SingleVisit singleVisit);

    /**
     * Inserting admission day to admission_days table.
     *
     * @param admissionDay data to insert.
     */
    @Insert("Insert into admissiondays(id_admission_day, id_doctor_working_day, date) " +
            "values (#{id}, #{doctorWorkingDay.id}, #{date})")
    void insertIntoAdmissionDays(AdmissionDay admissionDay);

    @Insert("INSERT into doctorworkingdays(id_doctor_working_day, id_doctor, day, hour_from, hour_to, hour_interval) " +
            "VALUES (#{doctorWorkingDayId}, #{doctorId}, #{day}, #{hourFrom}, #{hourTo}, #{hourInterval})")
    void insertIntoDoctorWorkingDays(DoctorWorkingDays doctorWorkingDay);


    /**
     * Selecting firstcontact doctor assigned to patient.
     *
     * @param doctorId  id of doctor to download.
     * @return          firstcontact doctor data.
     */
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


    /**
     * Selecting patient address from database.
     * It is using in getPatient as JOIN.
     *
     * @param addressId id of address to select.
     * @return          address assigned to patient.
     */
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


    /**
     * Selecting doctor working hours from database, using to get weekly doctor
     * schedule.
     *
     * @param doctorId  id of doctor to get data.
     * @return          List of doctor working days
     */
    @Select("select day, hour_from, hour_to, hour_interval from doctorworkingdays where id_doctor=#{doctorId}")
    @Results({
            @Result(property = "doctorId", column = "id_doctor"),
            @Result(property = "day", column = "day"),
            @Result(property = "hourFrom", column = "hour_from"),
            @Result(property = "hourTo", column = "hour_to"),
            @Result(property = "hourInterval", column = "hour_interval")
    })
    List<DoctorWorkingDays> getDoctorWorkingDays(int doctorId);

    /**
     * Returning all doctors, using to get them to select as specialist or fistcontact.
     *
     * @return all doctors.
     */
    @Select("select id_doctor, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_specialization from doctors")
    @Results(value = {
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


    @Select("select name from specializations")
    @Results(value = @Result(property = "name", column = "name"))
    List<String> getSpecializationsString();

    @Select("select name from specializations")
    @Results(value = @Result(property = "name", column = "name"))
    List<Specialization> getSpecializations();

    @Select("select first_name, last_name from doctors d INNER JOIN specializations s " +
            "where s.id_specialization = d.id_specialization AND s.name = #{specialization}")
    @Results(value = {
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    List<Doctor> getDoctorsBySpecialization(String specialization);

}
