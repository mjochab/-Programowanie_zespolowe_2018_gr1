package mappers;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.*;
import pojo.Doctor;

public interface DoctorMapper {

    @Select("SELECT first_name, last_name FROM doctors WHERE id_doctor=#{id}")
    @Results(value = {
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    Doctor get(int id);

}
