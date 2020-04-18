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
    List<Carrito> listaCarrito = new ArrayList<>();
    Producto producto = new Producto();
    Carrito carrito;
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;
    int idProducto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        listaProductos = productoDAO.listar();
        if (accion != null) {
            switch (accion) {
                case "Eliminar":
                    idProducto = Integer.parseInt(request.getParameter("idProducto"));
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (listaCarrito.get(i).getIdProducto() == idProducto) {
                            listaCarrito.remove(i);
                        }
                    }
                    break;
                case "Comprar":
                    totalPagar = 0.0;
                    listaCarrito.add(llenarCarrito(request));
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        totalPagar += listaCarrito.get(i).getSubTotal();
                    }
                    request.setAttribute("listaCarrito", listaCarrito);
                    request.setAttribute("contador", listaCarrito.size());
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("/WEB-INF/carrito.jsp").forward(request, response);
                    break;
                case "AgregarCarrito":
                    listaCarrito.add(llenarCarrito(request));
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
                    request.getRequestDispatcher("/WEB-INF/carrito.jsp").forward(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private Carrito llenarCarrito(HttpServletRequest request) {
        idProducto = Integer.parseInt(request.getParameter("id"));
        producto = productoDAO.listarId(idProducto);
        item++;
        carrito = new Carrito();
        carrito.setItem(item);
        carrito.setIdProducto(producto.getId());
        carrito.setNombres(producto.getNombres());
        carrito.setDescripcion(producto.getDescripcion());
        carrito.setPrecioCompra(producto.getPrecio());
        carrito.setCantidad(cantidad);
        carrito.setSubTotal(cantidad * producto.getPrecio());
        return carrito;
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
