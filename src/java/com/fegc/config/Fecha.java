package com.fegc.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author fausto
 */
public class Fecha {
    public static Calendar calendario = Calendar.getInstance();
    private static String fecha;

    public Fecha() {
    }
    
    public static String Fecha(){
        SimpleDateFormat formatearFecha = new SimpleDateFormat("dd-MM-yyyy");
        fecha = formatearFecha.format(calendario.getTime());
        return fecha;
    }
    
    public static String FechaBD(){
        SimpleDateFormat formatearFecha = new SimpleDateFormat("yyyy-MM-dd");
        fecha = formatearFecha.format(calendario.getTime());
        return fecha;
    }
    
}
