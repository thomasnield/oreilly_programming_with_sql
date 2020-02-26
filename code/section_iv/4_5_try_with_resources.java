import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaLauncher {

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite://C:/git/oreilly_programming_with_sql/thunderbird_manufacturing.db");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(5);

        final HikariDataSource ds = new HikariDataSource(config);

        // use try-catch with resources
        // this will automatically handle the disposal of resources
        // like connections and result sets
        // even when errors happen 
        try(final Connection conn = ds.getConnection()) {

            Statement stmt = conn.createStatement();
            try(ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT")) {
                while (rs.next()) {
                    System.out.println(rs.getInt("PRODUCT_ID") + " " + rs.getString("PRODUCT_NAME"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}