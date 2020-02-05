import tech.tablesaw.api.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaLauncher {

    // Documentation
    // https://jtablesaw.github.io/tablesaw/userguide/importing_data.html

    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_advanced_sql_for_data/thunderbird_manufacturing.db");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");

            Table customer = Table.read().db(rs);

            System.out.println(customer);

            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}