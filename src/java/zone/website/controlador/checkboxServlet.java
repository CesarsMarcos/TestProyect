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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zone.website.models.checkboxTest;
import zone.website.services.checkboxService;
import static zone.website.utils.conexion.getConexion;

/**
 *
 * @author César
 */
public class checkboxServlet extends HttpServlet {

    checkboxService modelo = new checkboxService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        switch (accion) {
            case "lista":
                lista(request, response);
                break;
            case "agregar":
                agregar(request, response);
                break;
            case "obtener":
                obtener(request, response);
                break;

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

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String hobbies = request.getParameterValues("hobbies")[0].toString();
        System.out.println(hobbies);
        String[] seleccion = hobbies.split(",");
        System.out.println(seleccion);
        // String hobbies = Arrays.toString(request.getParameterValues("hobbies"));

//        String[] seleccion = Arrays.stream(hobbies.substring(1, hobbies.length()-1).split(","))
//                .map(e -> e.replaceAll("\"", ""))
//                .toArray(String[]::new);
        int filasafectadas = 0;
        checkboxTest combo = new checkboxTest();
        if (seleccion != null && seleccion.length > 0) {

            for (String texto : seleccion) {
                if (texto.equalsIgnoreCase("Dancing")) {
                    combo.setCbo1(1);
                }
                if (texto.equalsIgnoreCase("Reading")) {
                    combo.setCbo2(1);
                }
                if (texto.equalsIgnoreCase("Singing")) {
                    combo.setCbo3(1);
                }
                if (texto.equalsIgnoreCase("Programming")) {
                    combo.setCbo4(1);
                }
                if (texto.equalsIgnoreCase("Sleeping")) {
                    combo.setCbo5(1);
                }
            }

//
//            for (int i = 0; i < seleccion.length; i++) {
//                System.out.println("DATA SELECCION" + seleccion[i]);
//                //hobbies += seleccion[i];
//                if (seleccion[i].matches("Dancing")) {
//                    combo.setCbo1(1);
//                }
//                if (seleccion[i].matches("Reading")) {
//                    combo.setCbo2(1);
//                }
//                if (seleccion[i].matches("Singing")) {
//                    combo.setCbo3(1);
//                }
//                if (seleccion[i].matches("Programming")) {
//                    combo.setCbo4(1);
//                }
//                if (seleccion[i].matches("Sleeping")) {
//                    combo.setCbo5(1);
//                }
//            }
        }
        filasafectadas = modelo.insertar(combo);

        if (filasafectadas > 0) {
            System.out.println("Registro agregado");
        } else {
            System.out.println("Un error ha ocurrido");
        }

    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            Integer codigo = Integer.parseInt(request.getParameter("id"));
            checkboxTest data = modelo.obtener(codigo);
            JsonObject json = new JsonObject();
            //json.addProperty("id", data.getCbo1());
            json.addProperty("Dancing",  data.getCbo1());
            json.addProperty("Reading",  data.getCbo2());
            json.addProperty("Singing",  data.getCbo3());
            json.addProperty("Programming",  data.getCbo4());
            json.addProperty("Sleeping",  data.getCbo5());
            out.print(json.toString());
            out.flush();
            System.out.println("JSON OBTENER DATA " + json.toString());
        } catch (Exception e) {
            System.out.println("zone.website.controlador.checkboxServlet.obtener() " + e.getMessage());
        }

    }

    private void lista(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=ISO-8859-1");
            request.setCharacterEncoding("UTF8");
            out = response.getWriter();
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT id_data,descripcion FROM cargardata";
                ps = getConexion().prepareStatement(sql);
                rs = ps.executeQuery();
                JsonArray array = new JsonArray();
                while (rs.next()) {
                    JsonObject item = new JsonObject();
                    item.addProperty("id_data", rs.getString("id_data"));
                    item.addProperty("descripcion", rs.getString("descripcion"));
                    array.add(item);
                }
                out.print(array.toString());
                System.out.println("JSON LISTA DATA " + array.toString());
            } catch (SQLException ex) {
                System.out.println("zone.website.controlador.checkboxServlet.listachk()" + ex.getMessage());
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(checkboxServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

}
