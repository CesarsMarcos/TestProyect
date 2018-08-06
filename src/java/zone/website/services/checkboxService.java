/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.website.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import zone.website.models.checkboxTest;
import zone.website.utils.conexion;

/**
 *
 * @author César
 */
public class checkboxService {

    public int insertar(checkboxTest checkbox) {
        int filasafectadas = 0;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO insertdata (Dancing,Reading,Singing,Programming,Sleeping)VALUES(?,?,?,?,?)";
            ps = conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, checkbox.getCbo1());
            ps.setInt(2, checkbox.getCbo2());
            ps.setInt(3, checkbox.getCbo3());
            ps.setInt(4, checkbox.getCbo4());
            ps.setInt(5, checkbox.getCbo5());
            filasafectadas = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("zone.website.services.checkboxService.insertar() " + e.getMessage());
        }

        return filasafectadas;
    }

    public checkboxTest obtener(Integer id) {
        checkboxTest data = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM insertdata WHERE id=? ";
            ps = conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                data = new checkboxTest();
                data.setCbo1(rs.getInt("Dancing"));
                data.setCbo2(rs.getInt("Reading"));
                data.setCbo3(rs.getInt("Singing"));
                data.setCbo4(rs.getInt("Programming"));
                data.setCbo5(rs.getInt("Sleeping"));
                return data;
            }
        } catch (Exception e) {
            System.out.println("zone.website.services.checkboxService.obtener() " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        checkboxService modelo = new checkboxService();
        System.out.println(modelo.obtener(1));

    }

}
