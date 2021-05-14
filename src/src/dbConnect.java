import java.sql.*;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Logger;

public class dbConnect {

    private static Logger logger;
    public static String dbFlag="";public static String pla;public static int min;public static int tim;public static int[] fie;
    public static String plaLoad;
    public static Connection getRemoteConnection() {
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
                //(String pla,int min,int tim,int[] fie)
                if(dbFlag.equals("save")) {
                    String insertsql = MessageFormat.format("INSERT INTO minesweep (playername,mine,time,field)" + " values ({0},{1},{2},{3})", pla, min, tim, fie);
                    Statement stmt=con.createStatement();
                    stmt.executeUpdate(insertsql);

                }else if(dbFlag.equals("load")){
                    String loadsql=MessageFormat.format("select * from minesweep where playername={0}",plaLoad);
                    Statement stmt=con.createStatement();
                    ResultSet res= stmt.executeQuery(loadsql);
                    while(res.next()){
                        //pla=res.getString("playername");
                        tim=res.getInt("time");
                        min=res.getInt("mine");
                        fie= (int[]) res.getObject ("field");
                    }
                }
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


}
