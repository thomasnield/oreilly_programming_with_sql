import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaLauncher {

    public static void main(String[] args) {

        try {
            HikariConfig config = new HikariConfig();
            //config.setJdbcUrl("jdbc:sqlite://C:/git/oreilly_programming_with_sql/thunderbird_manufacturing.db");
            config.setJdbcUrl("jdbc:sqlite:///Users/thomasnield/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");

            config.setMinimumIdle(1);
            config.setMaximumPoolSize(5);

            HikariDataSource ds = new HikariDataSource(config);

            // Get a connection from the pool
            Connection conn = ds.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");

            while (rs.next()) {
                System.out.println(rs.getInt("CUSTOMER_ID") + " " + rs.getString("CUSTOMER_NAME"));
            }
            //release connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
