
package com.fegc.controlador;

import com.fegc.modeloDAO.ProductoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fausto
 */
@WebServlet("/ControladorImagenes")
public class ControladorImagenes extends HttpServlet{
    ProductoDAO productoDAO = new ProductoDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productoDAO.listarImg2(id, response);
    }
    
}
