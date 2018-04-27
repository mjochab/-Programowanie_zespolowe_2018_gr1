import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

public class MyBatisDbConnection<T> {

    private Reader config;
    private SqlSession session;
    private Class<T> dataSourceProviderType;
    private T mapper;


    public MyBatisDbConnection(Class<T> type) {
        config = readFile();
        this.dataSourceProviderType = type;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        sqlSessionFactory.openSession();
        session = sqlSessionFactory.openSession();
    }


    private static Reader readFile() {
        try {
            return Resources.getResourceAsReader("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void openSession() {
        session.getConfiguration().addMapper(dataSourceProviderType.getClass());
        mapper = session.getMapper(dataSourceProviderType);
    }

    public T getMapper() {
        return mapper;
    }

    public void commit() {
        session.commit();
    }

    public void closeSession() {
        session.close();
    }
}
