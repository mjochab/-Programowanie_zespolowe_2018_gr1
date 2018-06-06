import database.MyBatisDbConnection;
import dto.PatientAdministrationDTO;
import dto.PatientModuleDTO;
import mappers.PatientAdministrationMapper;
import mappers.PatientModuleMapper;
import org.junit.jupiter.api.Test;
import pojo.AdmissionDay2;
import pojo.SingleVisit;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

public class PatientModuleTests extends DatabaseTestingAbstractClass {

    PatientModuleDTO dto = new PatientModuleDTO();

    public PatientModuleTests() throws NoSuchFieldException, IllegalAccessException {
        Field field = PatientModuleDTO.class.getDeclaredField("db");
        field.setAccessible(true);
        MyBatisDbConnection<PatientModuleMapper> a =
                new MyBatisDbConnection<>(PatientModuleMapper.class, "SqlMapConfig.xml");
        field.set(dto, a);
    }


    @Test
    void selectingAllVisitsForDateAndDortor_whenCheckDoctorAndSpecialization_returningVisitsOnlyForThisDoctorAndDate() {
        List<SingleVisit> result;
        LocalDate date = LocalDate.of(2018, 06, 06);
        AdmissionDay2 admissionDay = dto.getAdmissionDayForVisitPicker(date, 12);
        result = dto.getAllVisits(admissionDay);
        int x = 0;

    }



}
