/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fegc.controlador;

import com.fegc.modelo.Carrito;
import com.fegc.modelo.Producto;
import com.fegc.modelo.ProductoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fausto
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {

    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> listaProductos = new ArrayList<>();
    Producto producto = new Producto();
    List<Carrito> listaCarrito = new ArrayList<>();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String accion = request.getParameter("accion");        
        listaProductos = productoDAO.listar();        
        switch (accion) {
            case "AgregarCarrito":
                int id = Integer.parseInt(request.getParameter("id"));
                producto = productoDAO.listarId(id);
                item++;
                Carrito carrito = new Carrito();
                carrito.setItem(item);
                carrito.setIdProducto(producto.getId());
                carrito.setNombres(producto.getNombres());
                carrito.setDescripcion(producto.getDescripcion());
                carrito.setPrecioCompra(producto.getPrecio());
                carrito.setCantidad(cantidad);
                carrito.setSubTotal(cantidad * producto.getPrecio());
                listaCarrito.add(carrito);
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("listaCarrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar += listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            default:                
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
