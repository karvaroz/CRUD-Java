
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Connection getConnection(){
        String url ="jdbc:mysql://localhost:3306/dbusuarios";
        String user ="root";
        String pass ="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a la base de datos");
        } catch (Exception e){
          System.err.println(e);
        }
        return con;
    }
}
