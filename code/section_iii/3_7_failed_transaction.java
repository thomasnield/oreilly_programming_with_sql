import java.sql.*;

public class JavaLauncher {

    public static void main(String[] args) {

        try {

            Object[][] newRecords = {
                    {"Otter Antiques", "5732 Serenity Ln", "Addison", "TX", 75031, "COMMERCIAL"},
                    {"North Exa Energy", "67 Hearst Dr", "Plano", "TX", 75093, "INDUSTRIAL"},
                    {"City of Plano", "239 Plano Dr", "Plano", "TX" } // third item is missing data
            };

            //Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:///Users/thomasnield/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");

            // turn on transactions
            conn.setAutoCommit(false);

            // Insert 3 customers
            PreparedStatement ps = conn.prepareStatement("INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (?,?,?,?,?,?)");

            try {
                for (Object[] record : newRecords) {
                    if (record.length != 6) {
                        throw new Exception("Fields are missing for record!");
                    }
                    for (int i = 1; i <= record.length; i++) {
                        ps.setObject(i, record[i - 1]);
                    }
                    ps.execute(); // this should fail on the third INSERT
                }

                // commit transactions
                conn.commit();

            } catch(Exception e) {
                // roll back the transaction on failure
                conn.rollback();
                throw e;
            }

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
