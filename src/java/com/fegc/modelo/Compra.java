package com.fegc.modelo;

import java.util.List;

/**
 *
 * @author fausto
 */
public class Compra {
    private int id;
    private Cliente cliente;
    private int idPago;
    private String fecha;
    private double monto;
    private String estado;
    private List<Carrito> detalleCompras;

    public Compra() {
    }

    public Compra(Cliente cliente, int idPago, String fecha, double monto, String estado, List<Carrito> detalleCompras) {
        this.cliente = cliente;
        this.idPago = idPago;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.detalleCompras = detalleCompras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Carrito> getDetalleCompras() {
        return detalleCompras;
    }

    public void setDetalleCompras(List<Carrito> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", cliente=" + cliente + ", idPago=" + idPago + ", fecha=" + fecha + ", monto=" + monto + ", estado=" + estado + ", detalleCompras=" + detalleCompras + '}';
    }

    
   
    
    
}
