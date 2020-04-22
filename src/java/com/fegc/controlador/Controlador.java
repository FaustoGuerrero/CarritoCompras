package com.fegc.controlador;

import com.fegc.config.Fecha;
import com.fegc.modelo.Carrito;
import com.fegc.modelo.Cliente;
import com.fegc.modelo.Compra;
import com.fegc.modelo.Producto;
import com.fegc.modeloDAO.CompraDAO;
import com.fegc.modeloDAO.ProductoDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
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
                case "ActualizarCantidad":
                    idProducto = Integer.parseInt(request.getParameter("idProducto"));                    
                    cantidad = Integer.parseInt(request.getParameter("Cantidad"));                    
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (listaCarrito.get(i).getIdProducto() == idProducto) {
                            listaCarrito.get(i).setCantidad(cantidad);
                            double subtotal = listaCarrito.get(i).getPrecioCompra() * cantidad;
                            listaCarrito.get(i).setSubTotal(subtotal);                            
                        }
                    }                    
                    responseJSON(response, listaCarrito, accion);
                    break;
                case "Listar":
                    responseJSON(response, listaCarrito, accion);
                    break;
                case "Eliminar":
                    idProducto = Integer.parseInt(request.getParameter("idProducto"));
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (listaCarrito.get(i).getIdProducto() == idProducto) {
                            listaCarrito.remove(i);
                        }
                    }
                    responseJSON(response, listaCarrito, accion);
                    break;
                case "Comprar":
                    totalPagar = actualizarTotalCarrito();                    
                    listaCarrito.add(comprarArticulo(request));                    
                    request.setAttribute("listaCarrito", listaCarrito);
                    request.setAttribute("contador", listaCarrito.size());
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("/WEB-INF/carrito.jsp").forward(request, response);
                    break;
                case "AgregarCarrito":
                    llenarCarrito(request, response, accion);
                    break;
                case "Carrito":
                    totalPagar = actualizarTotalCarrito();                    
                    request.setAttribute("listaCarrito", listaCarrito);                    
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("/WEB-INF/carrito.jsp").forward(request, response);
                    break;
                case "GenerarCompra":
                    totalPagar = actualizarTotalCarrito();
                    Cliente cliente = new Cliente();
                    cliente.setId(1);
                    CompraDAO compraDAO = new CompraDAO();
                    Compra compra = new Compra(cliente, 1, Fecha.FechaBD(), totalPagar, "Cancelado", listaCarrito);
                    int res = compraDAO.generarCompra(compra);
                    System.out.println("Respuesta = " + res);
                    if (res != 0 && totalPagar > 0) {
                        request.getRequestDispatcher("/WEB-INF/mensaje.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                    }
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private double actualizarTotalCarrito() {
        totalPagar = 0.0;        
        for (int i = 0; i < listaCarrito.size(); i++) {
            totalPagar += listaCarrito.get(i).getSubTotal();
        }
        return totalPagar;
    }

    private void responseJSON(HttpServletResponse response, List<Carrito> listaCarrito, String nombreAccion) throws IOException {
        response.setContentType("application/json");
        OutputStream outputStream = response.getOutputStream();
        Gson gson = new Gson();
        if (nombreAccion.equals("Eliminar") || nombreAccion.equals("ActualizarCantidad")) {
            outputStream.write(gson.toJson(listaCarrito).getBytes());
            outputStream.flush();
        } else {
            outputStream.write(gson.toJson(listaCarrito.size()).getBytes());
            outputStream.flush();
        }
    }

    private Carrito comprarArticulo(HttpServletRequest request) {
        cantidad = 1;
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

    private void llenarCarrito(HttpServletRequest request, HttpServletResponse response, String nombreAccion) throws IOException {
        int pos = 0;
        cantidad = 1;
        idProducto = Integer.parseInt(request.getParameter("id"));
        producto = productoDAO.listarId(idProducto);
        if (listaCarrito.size() > 0) {
            for (int i = 0; i < listaCarrito.size(); i++) {
                if (idProducto == listaCarrito.get(i).getIdProducto()) {
                    pos = i;
                }
            }
            if (idProducto == listaCarrito.get(pos).getIdProducto()) {
                cantidad += listaCarrito.get(pos).getCantidad();
                double subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                listaCarrito.get(pos).setCantidad(cantidad);
                listaCarrito.get(pos).setSubTotal(subtotal);
                responseJSON(response, listaCarrito, nombreAccion);
            } else {
                item++;
                carrito = new Carrito();
                carrito.setItem(item);
                carrito.setIdProducto(producto.getId());
                carrito.setNombres(producto.getNombres());
                carrito.setDescripcion(producto.getDescripcion());
                carrito.setPrecioCompra(producto.getPrecio());
                carrito.setCantidad(cantidad);
                carrito.setSubTotal(cantidad * producto.getPrecio());
                listaCarrito.add(carrito);
                responseJSON(response, listaCarrito, nombreAccion);
            }
        } else {
            item++;
            carrito = new Carrito();
            carrito.setItem(item);
            carrito.setIdProducto(producto.getId());
            carrito.setNombres(producto.getNombres());
            carrito.setDescripcion(producto.getDescripcion());
            carrito.setPrecioCompra(producto.getPrecio());
            carrito.setCantidad(cantidad);
            carrito.setSubTotal(cantidad * producto.getPrecio());
            listaCarrito.add(carrito);
            responseJSON(response, listaCarrito, nombreAccion);
        }
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
    }

}
