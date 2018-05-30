package mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import pojo.PatientsHistory;

public interface PatientsHistoryMapper {
    @Insert("INSERT into files (id_file, id_patient, id_doctor, history) VALUES (#{fileId},#{patientId},#{doctorId},#{recognition})")
    @Options (useGeneratedKeys = true, keyProperty = "fileId", keyColumn = "id_file")
    void addPatientsHistory(PatientsHistory patientsHistory);
}
