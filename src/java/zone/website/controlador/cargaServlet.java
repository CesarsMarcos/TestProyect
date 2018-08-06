/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.website.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import zone.website.utils.conexion;
import static zone.website.utils.conexion.getConexion;

/**
 *
 * @author César
 */
@MultipartConfig(maxFileSize = 16999999)
public class cargaServlet extends HttpServlet {

    private static final int BUFFER_SIZE = 4096;

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        switch (accion) {
            case "upload":
                upload(request, response);
                break;
            case "download":
                donwload(request, response);
                break;
        }

    }

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

    private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PreparedStatement ps = null;
        PrintWriter out = response.getWriter();
        String tipoArchivo = null;
        String NombreArchivo = null;
        try {
            String descripcion = request.getParameter("descripcion");

            Part filePart = request.getPart("file");
            System.out.println("Nombre " + descripcion + " " + filePart);

            //ULTIMO AGREGADO
            final String path = getServletContext().getRealPath("/images_cargadas");
            File file = new File(path);
            file.mkdir();
            String fileName = getFileName(filePart);
            String url = "images_cargadas/" + fileName;

            //ULTIMO AGREGADO
            InputStream inputStream = null;
            if (filePart != null) {
                //DESCRIPCION DE ARCHIVO CARGADO
                tipoArchivo = filePart.getContentType();
                NombreArchivo = filePart.getName();
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                long fileSize = filePart.getSize();
                String fileContent = filePart.getContentType();
                inputStream = filePart.getInputStream();
            }

            //saveFileFromUrlWithJavaIO(fileName, url);
            int lineasafectadas = 0;
            String sql = "INSERT INTO file(nombre_archivo,tipo_archivo,descripcion,archivo) values(?,?,?,?)";
            ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, fileName);
            ps.setString(2, tipoArchivo);
            ps.setString(3, descripcion);
            if (inputStream != null) {
                ps.setBlob(4, inputStream);
            }
            lineasafectadas = ps.executeUpdate();
            if (lineasafectadas > 0) {
                out.println("Archivos cargados con exito");
                System.out.println("Cargado con exito");
            } else {
                out.println("Un error ha ocurrido");
                System.out.println("Un error ha ocurrido");
            }
        } catch (Exception e) {
            System.out.println("zone.website.controlador.cargaServlet.doPost() " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cargaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void donwload(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {

        int uploadId = Integer.parseInt(request.getParameter("uploadId"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM file WHERE id_upload=?";
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, uploadId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nombreFile = rs.getString("nombre_archivo");
                Blob blob = rs.getBlob("archivo");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();
                System.out.println("fileLength " + fileLength);

                ServletContext context = getServletContext();

                String mimeType = context.getMimeType(nombreFile);
                if (mimeType == null) {
                    mimeType = "aplication/octet-stram";
                }

                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", nombreFile);
                response.setHeader(headerKey, headerValue);

                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();

            } else {
                response.getWriter().print("File not found for the id: " + uploadId);
            }

        } catch (Exception e) {
            System.out.println("zone.website.controlador.cargaServlet.donwload()" + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cargaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cargaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
