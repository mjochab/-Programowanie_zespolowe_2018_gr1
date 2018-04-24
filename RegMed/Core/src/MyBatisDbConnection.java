import com.sun.org.apache.regexp.internal.recompile;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisDbConnection {

    public static final Reader config = readFile();
    private SqlSession session;

    private static Reader readFile() {
        try {
            return Resources.getResourceAsReader("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void openSession() {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        session = sqlSessionFactory.openSession();
    }

    public void closeSession() {
        session.close();
    }

}
