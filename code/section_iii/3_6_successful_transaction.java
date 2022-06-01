import java.sql.*;

public class JavaLauncher {

    public static void main(String[] args) {

        try {

            Object[][] newRecords = {
                    {"Otter Antiques", "5732 Serenity Ln", "Addison", "TX", 75031, "COMMERCIAL"},
                    {"North Exa Energy", "67 Hearst Dr", "Plano", "TX", 75093, "INDUSTRIAL"},
                    {"City of Plano", "239 Plano Dr", "Plano", "TX", 75093, "GOVERNMENT"}
            };

            //Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:///Users/thomasnield/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");

            // turn on transactions
            conn.setAutoCommit(false);

            // Insert 3 customers
            PreparedStatement ps = conn.prepareStatement("INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (?,?,?,?,?,?)");

            for (Object[] record : newRecords) {
                for (int i=1; i <= record.length; i++) {
                    ps.setObject(i, record[i-1]);
                }
                ps.execute();
            }

            // commit transactions
            conn.commit();

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
