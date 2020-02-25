import java.sql.*;

public class JavaLauncher {

    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_advanced_sql_for_data/thunderbird_manufacturing.db");

            // Delete customers with ID greater than 10
            PreparedStatement ps = conn.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMER_ID > ?");
            ps.setInt(1,10);

            ps.execute();


            // Update customers
            ps = conn.prepareStatement("UPDATE CUSTOMER SET STATE = lower(STATE)");
            ps.execute();

            // Iterate customers
            ResultSet rs = conn.prepareStatement("SELECT * FROM CUSTOMER").executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("CUSTOMER_ID") + " " + rs.getString("CUSTOMER_NAME") + " " + rs.getString("STATE"));
            }
            //release connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}