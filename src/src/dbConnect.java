import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class dbConnect {

    private static Logger logger;
    private static Connection getRemoteConnection() {
        System.out.println("cool");
            System.out.println("a");
            try {
                Class.forName("org.postgresql.Driver");
                String dbName = "krickey";
                String userName = "postgres";
                String password = "TV8943aa";
                String hostname = "postgres.c3obegeutpvq.us-east-2.rds.amazonaws.com";
                String port = "5432";
                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                //logger.trace("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl);
                System.out.println("Connection successful");
                //logger.info("Remote connection successful.");
                return con;
            }
            catch (ClassNotFoundException e) { //logger.warn(e.toString());
                System.out.println(e);
                e.printStackTrace();
                }
            catch (SQLException e) { //logger.warn(e.toString());
                System.out.println(e);
                e.printStackTrace();
                 }

        return null;
    }

    public static void main(String[] args){
           getRemoteConnection();
    }
}
