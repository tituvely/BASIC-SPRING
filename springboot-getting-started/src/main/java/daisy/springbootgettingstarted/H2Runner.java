package daisy.springbootgettingstarted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class H2Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // JDBC API
        Connection connection = dataSource.getConnection();
        System.out.println("url: " + connection.getMetaData().getURL());
        System.out.println("username: " + connection.getMetaData().getUserName());
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
        statement.executeUpdate(sql);
        statement.close();
        connection.close();

        // Connection 연결을 관리하지 않아도 되는 jdbcTemplate
        jdbcTemplate.execute("INSERT INTO USER VALUES(1, 'daisy')");
    }
}
