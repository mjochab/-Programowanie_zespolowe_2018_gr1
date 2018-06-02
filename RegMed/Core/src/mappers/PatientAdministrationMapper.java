package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Patient;

import java.util.List;

@Mapper
public interface PatientAdministrationMapper {


    @Select("SELECT id_patient, first_name, last_name, password, PESEL, id_address," +
            " email, phone_number,id_firstcontact_doctor FROM patients;")
    @Results({
            @Result(property = "patientId", column = "id_patient"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER)),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "firstContactDoctorId", column = "id_firstcontact_doctor")   //TODO: update db foreign key
    })
    List<Patient> getAllPatientsDataToTable();


    @Select("SELECT id_patient, first_name, last_name, PESEL, id_address," +
            " email, phone_number,id_firstcontact_doctor FROM patients " +
            "WHERE id_patient = #{patientId}")
    @Results({
            @Result(property = "patientId", column = "id_patient"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER)),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "firstContactDoctorId", column = "id_firstcontact_doctor")   //TODO: update db foreign key
    })
    Patient getPatient(int patientId);

    @Insert("INSERT into patients(id_patient, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_firstcontact_doctor, password) VALUES (#{patientId}, #{firstName}, #{lastName}, #{pesel}, #{address.addressId}," +
            "#{email}, #{phoneNumber}, #{firstContactDoctorId}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "patientId", keyColumn = "id_patient")
    void addPatient(Patient patient);


    @Insert("INSERT into addresses(id_address, city, zip_code, street, number) VALUES (" +
            "#{addressId}, #{city}, #{zip}, #{street}, #{number})")
    @Options(useGeneratedKeys = true, keyProperty = "addressId", keyColumn = "id_address")
    void addPatientAddressAsChild(Address address);

    @Update("UPDATE patients SET first_name=#{firstName}, last_name=#{lastName}, PESEL=#{pesel}, " +
            "email=#{email}, phone_number=#{phoneNumber} WHERE id_patient=#{patientId}")
    void updatePatient(Patient patient);

    @Update("UPDATE addresses SET city=#{city}, zip_code=#{zip}, street=#{street}, number=#{number} " +
            "WHERE id_address=#{addressId}")
    void updatePatientAddress(Address address);

    @Update("UPDATE patients SET id_firstcontact_doctor=#{newDoctorId} WHERE id_patient=#{patient.patientId}")
    void updatePatientFirstcontactDoctor(@Param("patient") Patient patient,
                                         @Param("newDoctorId") int newDoctorId);

    @Delete("DELETE from patients WHERE id_patient=#{patientId}")
    void deletePatient(int patientId);

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
}
