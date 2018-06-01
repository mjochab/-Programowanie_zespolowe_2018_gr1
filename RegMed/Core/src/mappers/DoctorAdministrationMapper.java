package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Doctor;
import pojo.Specialization;

import java.util.List;

/**
 * Mapping data from database to java classes and vice versa.
 * It is using MyBatis framework.
 *
 * @author Szymon P
 */
@Mapper
public interface DoctorAdministrationMapper {

    /**
     * Getting all doctors from database.
     *
     * @return all doctors from database written to list.
     */
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
            @Result(property = "specialization", column = "id_specialization",
                    javaType = Specialization.class, one = @One(select = "selectDoctorSpecialization",
                    fetchType = FetchType.EAGER))
    })
    List<Doctor> getAllDoctorsToTable();

    /**
     * Getting doctor by specified id.
     * Doctor containing his full address, which is taken by Join using
     * EagerLoading. So his address will be storing just in address field in
     * doctor POJO class.
     * @param doctorId  id of doctor, which you want to get from database.
     * @return          single doctor from database.
     */
    @Select("select id_doctor, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_specialization from doctors where id_doctor=#{doctorId}")
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
            @Result(property = "specialization", column = "id_specialization",
                    javaType = Specialization.class, one = @One(select = "selectDoctorSpecialization",
                    fetchType = FetchType.EAGER))
    })
    Doctor getDoctor(int doctorId);




    /**
     * Inserting specified doctor object into database.
     * Before adding doctor, you need to add address first, couse of constraints.
     *
     * @param doctor doctor to insert into database. Id is generated automatically.
     */
    @Insert("INSERT into doctors(id_doctor, first_name, last_name, PESEL, id_address, email, phone_number, " +
            "id_specialization, password) VALUES (#{id}, #{firstName}, #{lastName}, #{pesel}, #{address.addressId}, " +
            "#{email}, #{phoneNumber}, #{specialization.id}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id_doctor")
    void addDoctor(Doctor doctor);


    /**
     * Inserting specified address object into database.
     *
     * @param address address to insert into database. Id is generated automatically.
     */
    @Insert("INSERT into addresses(id_address, city, zip_code, street, number) VALUES (" +
            "#{addressId}, #{city}, #{zip}, #{street}, #{number})")
    @Options(useGeneratedKeys = true, keyProperty = "addressId", keyColumn = "id_address")
    void addDoctorAddressAsChild(Address address);


    /**
     * Updating doctor in database.
     * Object id will be used to take existing doctor from database, and update
     * his values using data from object (all except id).
     *
     * @param doctor all fields will be used for update doctor, which will have
     *               the same id like doctor in parameter.
     */
    @Update("UPDATE doctors SET first_name=#{firstName}, last_name=#{lastName}, PESEL=#{pesel}, " +
            "email=#{email}, phone_number=#{phoneNumber} WHERE id_doctor=#{id}")
    void updateDoctor(Doctor doctor);


    /**
     * Updating address in database.
     * Object id will be used to get address which you want update, and rest of then
     * for set new values.
     *
     * @param address object containing new data (except id).
     */
    @Update("UPDATE addresses SET city=#{city}, zip_code=#{zip}, street=#{street}, number=#{number} " +
            "WHERE id_address=#{addressId}")
    void updateDoctorAddress(Address address);


    //TODO: doctor specialization as FK
    /**
     * Updating doctor specialization to new value
     *
     * @param doctor            doctor which specialization you want to update.
     * @param specializationId  id of specialization, which you want set to
     *                          the doctor.
     */
    @Update("UPDATE doctors SET id_specialization=#{specializationId} WHERE id_doctor=#{doctor.id}")
    void updateDoctorSpecialization(@Param("doctor") Doctor doctor,
                                    @Param("specializationId") int specializationId);


    /**
     * Deleting doctor under specified id.
     *
     * @param doctorId  id of doctor to remove.
     */
    @Delete("DELETE from doctors WHERE id_doctor=#{doctorId};")
    void deleteDoctor(int doctorId);


    /**
     * Selecting address by specified id.
     * It is also join for selecting address to the get doctor(s).
     *
     * @param addressId id of address to return.
     * @return          address.
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

    @Select("SELECT id_specialization, name FROM specializations " +
            "WHERE id_specialization=#{specializationId}")
    @Results(value = {
            @Result(property = "id", column = "id_specialization"),
            @Result(property = "name", column = "name")
    })
    Specialization selectDoctorSpecialization(int specializationId);



    @Select("SELECT * FROM specializations")@Results(value = {
            @Result(property = "id", column = "id_specialization"),
            @Result(property = "name", column = "name")
    })
    List<Specialization> getAllSpecializations();




}
