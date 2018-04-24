import entities.Administrator;
import entities.Patient;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DbTests {

    @Test
    void connection_is_possible() throws SQLException {
        DatabaseLocalConnection db = new DatabaseLocalConnection();

        assertTrue(db.connection.isClosed() == false);
    }

    @Test
    void is_possible_toselect_querry() throws SQLException {
        DatabaseLocalConnection db = new DatabaseLocalConnection();
        String query = "SELECT * FROM administrators;";
        PreparedStatement ps = db.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        String result = null;
        if (rs.next()) {
            result = rs.getString(3);
        }

        assertTrue(result.contains("Jan"));
    }

    @Test
    void is_possible_to_create_user_from_selected_data() throws SQLException {
        DatabaseLocalConnection db = new DatabaseLocalConnection();
        Administrator admin = db.selectQuery("select * from administrators where id_administrator = 1;");


        assertTrue(admin != null);

        System.out.println(String.format("%i %s %s %s", admin.getId(), admin.getForename(), admin.getName(), admin.getPesel()));
    }

}
