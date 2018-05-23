package mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pojo.Address;
import pojo.Administrator;

import java.util.List;

/**
 * Mapping data from database to java classes and vice versa.
 * It is using MyBatis framework.
 *
 * @author Szymon P
 */
@Mapper
public interface AdminAdministrationMapper {

    /**
     * Getting all patients from database.
     *
     * @return all patients from database written to list.
     */
    @Select("select id_administrator, first_name, last_name, PESEL, email, phone_number " +
            "from administrators")
    @Results({
            @Result(property = "id", column = "id_administrator"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    List<Administrator> getAllAdministratorsToTable();

    /**
     * Getting administrator by specified id.
     *
     * @param adminId id of administrator, which you want to get from database.
     * @return single administrator from database.
     */
    @Select("select id_administrator, first_name, last_name, PESEL, email, phone_number " +
            "from administrators where id_administrator=#{adminId}")
    @Results({
            @Result(property = "id", column = "id_administrator"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "pesel", column = "PESEL"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
    })
    Administrator getAdministrator(int adminId);

    /**
     * Inserting specified administrator into database.
     *
     * @param administrator administrator to insert. Id is generated automatically.
     */
    @Insert("insert into administrators(id_administrator, first_name, last_name, PESEL, email, " +
            "phone_number, password) VALUES (#{id}, #{firstName}, #{lastName}, #{pesel}, #{email}, " +
            "#{phoneNumber}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id_administrator")
    void addAdministrator(Administrator administrator);

    /**
     * Updating specified administrator in database.
     *
     * @param administrator administrator to update.
     */
    @Update("update administrators set first_name=#{firstName}, last_name=#{lastName}, PESEL=#{pesel}, " +
            "email=#{email}, phone_number=#{phoneNumber} WHERE id_administrator=#{id}")
    void updateAdministrator(Administrator administrator);

    /**
     * Removing specified administrator, using id.
     *
     * @param adminId id of administrator which we want to remove.
     */
    @Delete("delete from administrators where id_administrator=#{adminId}")
    void deleteAdministrator(int adminId);


}
