package explora.trips;

import java.sql.*;  

public class Conn{
   Connection c;
   Statement s;
   Conn(){
       try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c = DriverManager.getConnection("jdbc:mysql:///ExploraTrips","root","Omesh@1998"); 
            s = c.createStatement();
            
         }catch(Exception e){}  
    }  
}