import pojo.Patient;
import mappers.PatientMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBatisDbTests {

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

}
