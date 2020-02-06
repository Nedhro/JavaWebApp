import java.sql.*;

public class LoginDao {
    public static boolean validate (String name, String pass){
        boolean status= false;
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

       // String url = "jdbc:mysql://localhost:3306";
        String url = "jdbc:postgresql://localhost:5432/";
        String dbName= "postgres";
        String driver= "org.postgresql.Driver";
        String userName= "postgres";
        String password= "1234";

        try{
            String query= "";
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            pst =conn.prepareStatement(query);
            pst.setString(1,name);
            pst.setString(2,pass);

            rs = pst.executeQuery();
            status =rs.next();
        }catch (Exception e){
            System.out.println(e);
        } finally {
            if (conn !=null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        if(pst !=null){
            try{
                pst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return status;
    }
}
