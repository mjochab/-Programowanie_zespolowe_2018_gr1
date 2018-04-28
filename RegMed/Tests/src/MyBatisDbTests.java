import mappers.DoctorMapper;
import org.junit.jupiter.api.Disabled;
import org.xml.sax.SAXException;
import pojo.Address;
import pojo.Doctor;
import pojo.Patient;
import mappers.PatientMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisDbTests {

    @Disabled
    @Test
    void is_possible_to_select_patient() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(PatientMapper.class);

        PatientMapper mapper = session.getMapper(PatientMapper.class);

        Patient patient = mapper.get(2);
        System.out.println(patient.getFirstName());
        System.out.println(patient.getLastName());
        System.out.println(patient.getEmail());

        //assertTrue(patient.getFirstName() == "Janusz");
    }

    @Test
    void connection_method_will_connect_and_return_type_is_correct() {
        MyBatisDbConnection<PatientMapper> dbConnection = new MyBatisDbConnection<PatientMapper>(PatientMapper.class);
        dbConnection.openSession();
        //PatientMapper mapper = dbConnection.getMapper();

        //Patient patient = mapper.get(2);
        Patient result = dbConnection.getMapper().get(2);
        dbConnection.closeSession();

        System.out.println(result.getFirstName());
        System.out.println(result.getLastName());
        System.out.println(result.getEmail());

        assertEquals("Janusz", result.getFirstName());
        assertEquals(1, result.getGlobalId());
    }

    @Test
    void get_all_patients_returning_2_patients() {
        MyBatisDbConnection<PatientMapper> dbConnection = new MyBatisDbConnection<PatientMapper>(PatientMapper.class);
        dbConnection.openSession();

        List<Patient> result = dbConnection.getMapper().getAll();

        System.out.println("Size: " + result.size());

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getFirstName());
        }

        assertTrue(result.size() == 2);
        assertEquals("Sebastian", result.get(1).getFirstName());
    }

    @Test
    void is_possible_to_get_doctor() {
        MyBatisDbConnection<DoctorMapper> dbConnection = new MyBatisDbConnection<DoctorMapper>(DoctorMapper.class);
        dbConnection.openSession();
        Doctor doctor = dbConnection.getMapper().get(1);


        System.out.println(doctor.getFirstName()+" "+doctor.getLastName());

    }

    @Test
    void is_possible_to_get_doctor_with_address() {
        MyBatisDbConnection<DoctorMapper> dbConnection = new MyBatisDbConnection<DoctorMapper>(DoctorMapper.class);
        dbConnection.openSession();
        Doctor doctor = dbConnection.getMapper().get(1);


        System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
        System.out.println(String.format("%s %s %s %s",
                doctor.getAddress().getCity(),
                doctor.getAddress().getZip(),
                doctor.getAddress().getStreet(),
                String.valueOf(doctor.getAddress().getNumber())
        ));

        assertNotNull(doctor.getAddress().getZip());
    }



}
