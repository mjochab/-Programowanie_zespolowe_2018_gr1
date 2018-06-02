package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Doctor;

import java.util.List;

@Mapper
public interface DoctorAdministrationMapper {

    @Select("select id_doctor, first_name, last_name,password, PESEL, id_address, email, phone_number, " +
            "id_specialization from doctors")
    @Results({
            @Result(property = "doctorId", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER)),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "specializationId", column = "id_specialization")
    })
    List<Doctor> getAllDoctorsToTable();

    @Select("select id_doctor, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_specialization from doctors where id_doctor=#{doctorId}")
    @Results({
            @Result(property = "doctorId", column = "id_doctor"),
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
    Doctor getDoctor(int doctorId);


    @Insert("INSERT into doctors(id_doctor, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_specialization, password) VALUES (#{doctorId}, #{firstName}, #{lastName}, #{pesel}, #{address.addressId}, " +
            "#{email}, #{phoneNumber}, #{specializationId}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "doctorId", keyColumn = "id_doctor")
    void addDoctor(Doctor doctor);

    @Insert("INSERT into addresses(id_address, city, zip_code, street, number) VALUES (" +
            "#{addressId}, #{city}, #{zip}, #{street}, #{number})")
    @Options(useGeneratedKeys = true, keyProperty = "addressId", keyColumn = "id_address")
    void addDoctorAddressAsChild(Address address);

    @Update("UPDATE doctors SET first_name=#{firstName}, last_name=#{lastName}, PESEL=#{pesel}, " +
            "email=#{email}, phone_number=#{phoneNumber} WHERE id_doctor=#{doctorId}")
    void updateDoctor(Doctor doctor);

    @Update("UPDATE addresses SET city=#{city}, zip_code=#{zip}, street=#{street}, number=#{number} " +
            "WHERE id_address=#{addressId}")
    void updateDoctorAddress(Address address);

    @Update("UPDATE doctors SET id_specialization=#{specializationId} WHERE id_doctor=#{doctor.doctorId}")
    void updateDoctorSpecialization(@Param("doctor") Doctor doctor,
                                    @Param("specializationId") int specializationId);


    @Delete("DELETE from doctors WHERE id_doctor=#{doctorId};")
    void deleteDoctor(int doctorId);



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
