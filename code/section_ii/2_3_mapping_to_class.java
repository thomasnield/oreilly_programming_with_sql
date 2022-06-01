import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaLauncher {

    public static void main(String[] args) {

        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:///Users/thomasnield/git/oreilly_intermediate_sql_for_data/thunderbird_manufacturing.db");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from CUSTOMER");

            List<Customer> customers = new ArrayList<>();

            while (rs.next()) {
                customers.add(
                        new Customer(rs.getInt("CUSTOMER_ID"),
                                rs.getString("CUSTOMER_NAME"),
                                rs.getString("ADDRESS"),
                                rs.getString("CITY"),
                                rs.getString("STATE"),
                                rs.getInt("ZIP"),
                                rs.getString("CATEGORY")
                        )
                );
            }

            System.out.println(customers);

            //release connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Customer {

        private final int customerId;
        private final String customerName;
        private final String address;
        private final String city;
        private final String state;
        private final int zip;
        private final String category;

        public Customer(int customerId, String customerName, String address, String city, String state, int zip, String category) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.category = category;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public int getZip() {
            return zip;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "customerId=" + customerId +
                    ", customerName='" + customerName + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", zip=" + zip +
                    ", category='" + category + '\'' +
                    '}';
        }
    }
}
