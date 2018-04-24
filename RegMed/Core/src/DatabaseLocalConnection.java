import entities.Administrator;

import java.sql.*;
import java.util.List;

public class DatabaseLocalConnection {

    public Connection connection;

    public DatabaseLocalConnection() {
        connection = connect();
    }

    private Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/regmedtests";
            String user = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conection = DriverManager.getConnection(url, user, password);
            return conection;
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean checkConnection() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Administrator selectQuery(String query){
        if (!checkConnection()) {
            connect();
        }

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            Administrator admin = null;
            if (rs.next()) {

                admin = rs.getObject(1, Administrator.class);
            }
            closeConnection();
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



    }
}
