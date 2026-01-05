
package COM.DINUSHA.MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {
    
    private static Connection connection;
    
    public static void CreateConnection(){
        try {
            if(connection == null){
               Class.forName("com.mysql.cj.jdbc.Driver");
              // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagementsystem","root","Dinusha@0225.");
              connection =  DriverManager.getConnection("jdbc:mysql://horizon-abdullahfareed882-dc96.h.aivencloud.com:22617/aluth_iskole","avnadmin","AVNS_Qj9QuPi5A4D2liO-nw4");
        
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no connection");
            e.printStackTrace();
        }
    }
    
    public static ResultSet executeSearch(String query) throws Exception{
        CreateConnection();
        System.out.println(query);
        return connection.createStatement().executeQuery(query);
        
    }
    
      public static Integer executeIUD(String query) throws Exception{
        CreateConnection();
        System.out.println(query);
        return connection.createStatement().executeUpdate(query);
    }
    
}
