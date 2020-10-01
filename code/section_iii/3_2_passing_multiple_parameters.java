import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaLauncher {

    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE STATE = ? AND CATEGORY = ?");

            stmt.setString(1, "TX");
            stmt.setString(2, "COMMERCIAL");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("CUSTOMER_ID") + "," + rs.getString("CUSTOMER_NAME") + "," + rs.getString("STATE") + "," + rs.getString("CATEGORY"));
            }

            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}