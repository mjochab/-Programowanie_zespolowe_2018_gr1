package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Administrator;

import java.util.List;

@Mapper
public interface AdminAdministrationMapper {

    @Select("select id_administrator, first_name, last_name, PESEL, email, phone_number " +
            "from administrators")
    @Results({
            @Result(property = "adminId", column = "id_administrator"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    List<Administrator> getAllAdministratorsToTable();

    @Select("select id_administrator, first_name, last_name, PESEL, email, phone_number " +
            "from administrators where id_administrator=#{adminId}")
    @Results({
            @Result(property = "adminId", column = "id_administrator"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    Administrator getAdministrator(int adminId);


    @Insert("insert into administrators(id_administrator, first_name, last_name, PESEL, email, " +
            "phone_number, password) VALUES (#{adminId}, #{firstName}, #{lastName}, #{pesel}, #{email}, " +
            "#{phoneNumber}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId", keyColumn = "id_administrator")
    void addAdministrator(Administrator administrator);


    @Update("update administrators set first_name=#{firstName}, last_name=#{lastName}, PESEL=#{pesel}, " +
            "email=#{email}, phone_number=#{phoneNumber} WHERE id_administrator=#{adminId}")
    void updateAdministrator(Administrator administrator);


    @Delete("delete from administrators where id_administrator=#{adminId}")
    void deleteAdministrator(int adminId);


}
