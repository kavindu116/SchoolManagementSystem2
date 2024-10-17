
package Model;

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
               connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SchoolManagementSystem","root","Kavindu@2003Lakshan");
            }
        } catch (ClassNotFoundException | SQLException e) {
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
