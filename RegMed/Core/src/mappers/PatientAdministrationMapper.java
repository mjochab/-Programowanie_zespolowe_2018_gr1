package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Doctor;
import pojo.Patient;
import pojo.Specialization;

import java.util.List;

/**
 * Mapping data from database to java classes and vice versa.
 * It is using MyBatis framework.
 *
 * @author Szymon P
 */
@Mapper
public interface PatientAdministrationMapper {

    /**
     * Getting all patients from database.
     *
     * @return all patients from database written to list.
     */
    @Select("SELECT id_patient, first_name, last_name, PESEL, id_address," +
            " email, phone_number,id_firstcontact_doctor FROM patients;")
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
            @Result(property = "firstContactDoctor", column = "id_firstcontact_doctor",
                    javaType = Doctor.class, one = @One(select = "selectFirstcontactDoctor",
                    fetchType = FetchType.EAGER))
    })
    List<Patient> getAllPatientsDataToTable();


    /**
     * Getting patient by specified id.
     * Patient containing his full address, which is taken by Join using
     * EagerLoading. So his address will be storing just in address field in
     * patient POJO class.
     *
     * @param patientId id of patient, which you want to get from database.
     * @return          single patient taken from database.
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
            @Result(property = "firstContactDoctor", column = "id_firstcontact_doctor",
                    javaType = Doctor.class, one = @One(select = "selectFirstcontactDoctor",
                    fetchType = FetchType.EAGER))
    })
    Patient getPatient(int patientId);


    /**
     * Inserting specified patient object into database.
     * Before adding patient, you need to add address first, couse of constraints.
     *
     * @param patient patient to insert into database. Id is generated automatically.
     */
    @Insert("INSERT into patients(id_patient, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_firstcontact_doctor, password) VALUES (#{id}, #{firstName}, #{lastName}, #{pesel}, #{address.addressId}," +
            "#{email}, #{phoneNumber}, #{firstContactDoctor.id}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id_patient")
    void addPatient(Patient patient);


    /**
     * Inserting specified address object into database.
     *
     * @param address address to insert into database. Id is generated automatically.
     */
    @Insert("INSERT into addresses(id_address, city, zip_code, street, number) VALUES (" +
            "#{addressId}, #{city}, #{zip}, #{street}, #{number})")
    @Options(useGeneratedKeys = true, keyProperty = "addressId", keyColumn = "id_address")
    void addPatientAddressAsChild(Address address);


    /**
     * Updating patient in database.
     * Object id will be used to take existing doctor from database, and update
     * his values using data from object (all except id).
     *
     * @param patient all fields will be used for update patient, which will have
     *                the same id like doctor in parameter.
     */
    @Update("UPDATE patients SET first_name=#{firstName}, last_name=#{lastName}, PESEL=#{pesel}, " +
            "email=#{email}, phone_number=#{phoneNumber} WHERE id_patient=#{id}")
    void updatePatient(Patient patient);


    /**
     * Updating address in database.
     * Object id will be used to get address which you want update, and rest of then
     * for set new values.
     *
     * @param address object containing new data (except id).
     */
    @Update("UPDATE addresses SET city=#{city}, zip_code=#{zip}, street=#{street}, number=#{number} " +
            "WHERE id_address=#{addressId}")
    void updatePatientAddress(Address address);


    /**
     * Updating patient firstcontact doctor using his id.
     *
     * @param patient       patient, which firstcontact doctor you want update.
     * @param newDoctorId   id of new patient firstcontact doctor.
     */
    @Update("UPDATE patients SET id_firstcontact_doctor=#{newDoctorId} WHERE id_patient=#{patient.id}")
    void updatePatientFirstcontactDoctor(@Param("patient") Patient patient,
                                         @Param("newDoctorId") int newDoctorId);

    /**
     * Deleting patient specified by id
     *
     * @param patientId id of patient to remove.
     */
    @Delete("DELETE from patients WHERE id_patient=#{patientId}")
    void deletePatient(int patientId);


    //TODO: it's necessary? Address should be dropped cascade. Test it.
    /**
     * Deleting address
     *
     * @param addressId id of address to remove.
     */
    @Delete("DELETE from addresses WHERE id_address=#{addressId}")
    void deleteAddress(int addressId);









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

    @Select("Select id_doctor, first_name, last_name, phone_number, id_specialization " +
            "from doctors where id_doctor=#{doctorId}")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "specialization", column = "id_specialization",
                    javaType = Specialization.class,
                    one = @One(select = "selectFirstcontactDoctorSpecialization",
                            fetchType = FetchType.EAGER))
    })
    Doctor selectFirstcontactDoctor(int doctorId);

    @Select("SELECT * FROM specializations " +
            "WHERE id_specialization=#{specializationId}")
    @Results(value = {
            @Result(property = "id", column = "id_specialization"),
            @Result(property = "name", column = "name")
    })
    Specialization selectFirstcontactDoctorSpecialization(int specializationId);


    @Select("Select id_doctor, first_name, last_name, phone_number, d.id_specialization from doctors d INNER JOIN specializations AS s\n" +
            "WHERE d.id_specialization = s.id_specialization AND s.name=\"First contact\"")
    @Results(value = {
            @Result(property = "id", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "specialization", column = "id_specialization",
                    javaType = Specialization.class,
                    one = @One(select = "selectFirstcontactDoctorSpecialization",
                            fetchType = FetchType.EAGER))
    })
    List<Doctor> getAllFirstContactDoctors();



}
