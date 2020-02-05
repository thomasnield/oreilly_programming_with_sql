import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaLauncher {

    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_advanced_sql_for_data/thunderbird_manufacturing.db");
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (?,?,?,?,?,?)");

            insertStmt.setString(1, "Brittania Solutions, LLC");
            insertStmt.setString(2, "2374 Collie Way");
            insertStmt.setString(3, "Frisco");
            insertStmt.setString(4, "TX");
            insertStmt.setInt(5, 75035);
            insertStmt.setString(6, "COMMERCIAL");

            // Execute INSERT
            insertStmt.execute();

            // Select all records to check
            PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM CUSTOMER");
            ResultSet rs = selectStmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("CUSTOMER_ID") + "," + rs.getString("CUSTOMER_NAME") + "," + rs.getString("STATE") + "," + rs.getString("CATEGORY"));
            }

            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}