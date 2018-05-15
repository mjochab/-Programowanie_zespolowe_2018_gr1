import dto.AdminAdministrationDTO;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.Administrator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//DB MUST BE REMOVED FROM SERVER BEFORE TESTS

public abstract class DatabaseTestingAbstractClass {

    private static final String XML_PATH = "../../regmedtests _no_id_factory.sql";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "";
    private static final String DB_NAME = "regmedtests";    //You shouldn't change it.

    @BeforeEach
    public void createDb() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String url = "jdbc:mysql://localhost:3306/";
        String user = DB_LOGIN;
        String password = DB_PASSWORD;
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE " + DB_NAME);

        connection.close();
        setDbSqlFile();
    }


    public void setDbSqlFile() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String url = String.format("jdbc:mysql://localhost:3306/%s", DB_NAME);
        String user = DB_LOGIN;
        String password = DB_PASSWORD;
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, user, password);

        ScriptRunner runner = new ScriptRunner(connection);
        runner.runScript(new BufferedReader(new FileReader(XML_PATH)));
    }


    @AfterEach
    public void dropDb() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String url = String.format("jdbc:mysql://localhost:3306/%s", DB_NAME);
        String user = DB_LOGIN;
        String password = DB_PASSWORD;
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement;

        statement = connection.createStatement();
        statement.executeUpdate("DROP DATABASE " + DB_NAME);


    }












}
