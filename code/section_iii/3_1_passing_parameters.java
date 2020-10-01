import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaLauncher {

    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE CATEGORY = ?");

            stmt.setString(1, "COMMERCIAL");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("CUSTOMER_NAME") + " is categorized as " + rs.getString("CATEGORY"));
            }

            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}