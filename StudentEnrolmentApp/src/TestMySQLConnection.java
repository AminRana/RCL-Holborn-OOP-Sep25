import java.sql.Connection;
import java.sql.DriverManager;

public class TestMySQLConnection {
    public static void main(String[] args) {
        try {
            // Test if MySQL driver is available
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Connector Driver loaded successfully!");

            // Test connection
            String url = "jdbc:mysql://localhost:3306/enrolment_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = "380765";

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL database successfully!");

            con.close();
            System.out.println("✅ Connection closed successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Connector Driver not found!");
            System.err.println("Please ensure mysql-connector-j-9.5.0.jar is in the classpath.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Failed to connect to MySQL database!");
            System.err.println("Please ensure MySQL server is running and credentials are correct.");
            e.printStackTrace();
        }
    }
}
