import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaLauncher {

    public static void main(String[] args) {

        try {
            HikariConfig config = new HikariConfig();
            //config.setJdbcUrl("jdbc:sqlite://C:/git/oreilly_programming_with_sql/thunderbird_manufacturing.db");
            config.setJdbcUrl("jdbc:sqlite:///Users/thomasnield/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");
            
            config.setMinimumIdle(1);
            config.setMaximumPoolSize(5);

            final HikariDataSource ds = new HikariDataSource(config);

            Runnable task1 = () -> {
                try {
                    // Get a connection from the pool
                    Connection conn = ds.getConnection();

                    Statement stmt = conn.createStatement();

                    ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");

                    while (rs.next()) {
                        System.out.println("QUERY 1: " + rs.getInt("CUSTOMER_ID") + " " + rs.getString("CUSTOMER_NAME"));
                    }
                    //release connection
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
            Runnable task2 = () -> {
                try {
                    // Get a connection from the pool
                    Connection conn = ds.getConnection();

                    Statement stmt = conn.createStatement();

                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

                    while (rs.next()) {
                        System.out.println("QUERY 2: " + rs.getInt("PRODUCT_ID") + " " + rs.getString("PRODUCT_NAME"));
                    }
                    //release connection
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };

            // Create executor service with 2 threads
            // Schedule both tasks and shutdown when they are complete
            // We should see both sets of records iterated simultaneously 
            ExecutorService exec = Executors.newFixedThreadPool(2);
            exec.submit(task1);
            exec.submit(task2);
            exec.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
