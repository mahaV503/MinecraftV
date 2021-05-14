import java.sql.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class dbConnect {

    private static Logger logger;
    public static String dbFlag="";
    public static String pla;
    public static int min;
    public static int tim;
    public static int[] fie;
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
                    Array arrayUSA = con.createArrayOf("INTEGER", new int[][]{fie});
                    String sql = "INSERT INTO minesweep VALUES (?, ?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(sql);

                    pstmt.setString(1, pla);
                    pstmt.setInt(2,min);
                    pstmt.setInt(3,tim);
                    pstmt.setArray(4, arrayUSA);
                    pstmt.executeUpdate();
                }else if(dbFlag.equals("load")){

                    //String loadsql=MessageFormat.format("select * from minesweep where playername='{0}'",plaLoad);
                    String loadsql="select * from minesweep where playername='"+plaLoad+"'";
                    Statement stmt=con.createStatement();
                    ResultSet res= stmt.executeQuery(loadsql);

                    if(res.next()) {
                        //pla=res.getString("playername");
                        tim = res.getInt("time");
                        min = res.getInt("mine");
                        Array tempfie = res.getArray("field");
                        System.out.println(tempfie.toString());
                        fie=theCorrector(tempfie.toString());
                        //fie = (int[]) tempfie.getArray();
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

    private static int[] theCorrector(String toString) {
        toString=toString.substring(2,toString.length()-2);
        int [] v = Stream.of(toString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        return v;
    }


}
