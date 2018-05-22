package database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

/**
 * Using for connection with database.
 *
 * @param <T>   mapper type using in connection.
 * @author      Szymon P
 */
public class MyBatisDbConnection<T> {


    private Reader config;
    private SqlSession session;
    private Class<T> dataSourceProviderType;
    private T mapper;
    private SqlSessionFactory sqlSessionFactory;

    /**
     * Creating connection with database
     * @param type mapper type using in connection
     */
    public MyBatisDbConnection(Class<T> type) {
        config = readFile("database/SqlMapConfig.xml");
        this.dataSourceProviderType = type;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
    }

    /**
     * Creating connection with database
     *
     * @param type              mapper type using in connection
     * @param sqlMapConfigFile  path to configuration file containing
     *                          database and mappers information.
     */
    public MyBatisDbConnection(Class<T> type, String sqlMapConfigFile) {
        config = readFile(sqlMapConfigFile);
        this.dataSourceProviderType = type;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
    }

    /**
     * Reading xml file with database configuration.
     *
     * @param src       path to configuration file.
     * @return reader   containing xml configuration file.
     */
    private static Reader readFile(String src) {
        try {
            return Resources.getResourceAsReader(src);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creating session connected with database.
     */
    public void openSession() {

        session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(dataSourceProviderType.getClass());
        //session.getConfiguration().setLazyLoadingEnabled(false);
        mapper = session.getMapper(dataSourceProviderType);
    }

    /**
     * Getting mapper, which is using to invoke methods from mapped interface
     *
     * @return Mapper using to induce method using for work with database, declared in mapper included in constructor.
     */
    public T getMapper() {
        return mapper;
    }

    /**
     * Committing data to database.
     */
    public void commit() {
        session.commit();
    }

    /**
     * Closing session with database.
     */
    public void closeSession() {
        session.close();
    }
    
}
