package mappers;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.mapping.StatementType;
import pojo.Address;
import pojo.Doctor;

@Mapper
public interface DoctorMapper {


    @Select("SELECT id_doctor,first_name, last_name, id_address FROM doctors d " +
            "WHERE id_doctor=#{doctorId}")
    @Results(value = {
            @Result(property = "doctorId", column = "id_doctor"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "address", column = "id_address",
                    javaType = Address.class, one = @One(select = "selectAddress",
                    fetchType = FetchType.EAGER))   //Can be remove, but then data can be load eager or lazy
    })
    Doctor get(int doctorId);

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
