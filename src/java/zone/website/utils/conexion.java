/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.website.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author César
 */
public class conexion {
    
    private static String url = "jdbc:mysql://localhost/testUpload";
    private static String user = "root";
    private static String pass = "1234";
    private static String driver = "com.mysql.jdbc.Driver";

    public static Connection getConexion() throws SQLException {
     
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, pass);
            System.out.println("CONEXION EXITOSA");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al conectar" + e.getMessage());
        }
        return cn;
    }

    public static void main(String[] args) throws SQLException {
        conexion.getConexion();
    }

    
    /* conexion sql
    public Connection conectar(){
        Connection cn= null;   
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;NombeBd", "sa","123");
            if(cn.isValid(0)){
                System.out.println("CONEXION EXITOSA");
            }else{
                System.err.println("ERROR AL CONECTAR ");
            }
        } catch (Exception e) {
            System.err.println("ERROR AL CONECTAR "+e.getMessage());
        }
        return  cn;
    }
*/
}
