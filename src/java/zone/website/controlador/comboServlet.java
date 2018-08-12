/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.website.controlador;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zone.website.utils.conexion;

/**
 *
 * @author César
 */
public class comboServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        switch (accion) {
            case "listaequipo":
                listacombo(request, response);
            case "listahincha":
                listahincha(request, response);
        }
    }

    private void listacombo(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            Connection cn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT id_equipo,descripcion_equipo FROM equipo";
                cn = conexion.getConexion();
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                JsonArray array = new JsonArray();
                while (rs.next()) {
                    JsonObject item = new JsonObject();
                    item.addProperty("id", rs.getInt("id_equipo"));
                    item.addProperty("descripcion", rs.getString("descripcion_equipo"));
                    array.add(item);
                }
                out.print(array.toString());
                out.flush();
                out.close();

            } catch (Exception ex) {
                System.out.println("zone.website.controlador.solicitudServlet.listacombo() " + ex.getMessage());
            } finally {
                out.flush();
                out.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(comboServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.flush();
            out.close();
        }
    }

    private void listahincha(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));

        PrintWriter out = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JsonObject json = new JsonObject();
        JsonArray array = new JsonArray();

        try {
            String sql = "SELECT id_hincha,descripcion_hincha FROM hincha WHERE id_equipo=?";
            cn = conexion.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("ids", rs.getInt("id_hincha"));
                item.addProperty("descripcion_hincha", rs.getString("descripcion_hincha"));
                array.add(item);
            }
            json.add("datos", array);
            out.print(json.toString());
            out.close();
            System.out.println(json.toString());

        } catch (Exception e) {
            System.out.println("zone.website.controlador.comboServlet.listahincha()" + e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
