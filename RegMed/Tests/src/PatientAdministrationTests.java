import database.MyBatisDbConnection;
import dto.AdminAdministrationDTO;
import dto.DoctorAdministrationDTO;
import dto.PatientAdministrationDTO;
import mappers.AdminAdministrationMapper;
import mappers.PatientAdministrationMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pojo.Patient;
import pojo.SingleVisit;
import pojo.Specialization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PatientAdministrationTests extends DatabaseTestingAbstractClass {

    PatientAdministrationDTO dto = new PatientAdministrationDTO();

    public PatientAdministrationTests() throws NoSuchFieldException, IllegalAccessException {
        Field field = PatientAdministrationDTO.class.getDeclaredField("dbConnection");
        field.setAccessible(true);
        MyBatisDbConnection<PatientAdministrationMapper> a =
                new MyBatisDbConnection<>(PatientAdministrationMapper.class, "SqlMapConfig.xml");
        field.set(dto, a);
    }

    @Test
    void deletePatient_deletingPatinets_WhenAsloDeletingVisits() {
        List<Patient> before = dto.getAll();
        List<Patient> result;

        dto.deleteAllVisits(2);
        dto.deleteAllVisits(4);
        dto.delete(2);
        dto.delete(4);
        result = dto.getAll();

        assertTrue(result.size() == before.size()-2);
    }

    @Test
    void deleteVisit_deletingCorrectVisit() {
        List<SingleVisit> before = dto.getVisitsForPatient(1);
        List<SingleVisit> result;
        dto.deleteVisit(26);
        result = dto.getVisitsForPatient(1);

        assertTrue(result.size() == before.size()-1);
        assertTrue(dto.getVisit(26) == null);
    }

}
