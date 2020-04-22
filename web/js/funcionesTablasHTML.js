//   //alert("Todo salio bien " + JSON.stringify(ajax));
//Para actualizar la pagina --> parent.location.href = "Controlador?accion=Carrito"
//
////var table =  document.getElementById("tablaDatos"), rIndex, cantidad, subtotal;                
//        for(var i=1; i<table.rows.length;i++){
//                table.rows[i].onclick = function (){
//                    rIndex = this.rowIndex;                    
////                    console.log("Celda input "+this.cells[3].querySelector('input').value);                    
////                    console.log("Celda subtotal "+this.cells[4].innerHTML);
////                    console.log("Celda input "+this.cells[3].querySelector('input').value);                    
////                    console.log("idProducto "+idProducto);//                    
//                    //console.log("Cantidad "+cantidad);
//                    cantidad = this.cells[3].querySelector('input').value;
//                    subtotal = this.cells[4].innerHTML;                    
//                };                     
//        }


//subtotal = this.cells[4].innerHTML;
            //nuevoSubtotal = parseFloat(cantidad) * parseFloat(subtotal.substr(1));
            // this.cells[4].innerHTML = "$"+nuevoSubtotal.toFixed(2).replace('.', ',');
            //       //console.log("Fila "+i+" "+table.rows[i].cells[4].innerHTML);
        //console.log("Valor substring "+i+" "+(table.rows[i].cells[4]).substr(1));
        //
        //
        ////    
//    var tablaActualizada = document.getElementById("tablaDatos");
//    var subtotal2 = 0;
//    for (var i = 1; i < tablaActualizada.rows.length; i++) {
//        console.log("Subtotal fila "+i+" = "+ tablaActualizada.rows[i].cells[4].innerHTML);
//        subtotal2 += parseFloat((tablaActualizada.rows[i].cells[4].innerHTML).substr(1));
//    }
  
//AJAX
/*
 * var url = "Controlador?accion=Eliminar";
    $.ajax({
        type: 'POST',
        url: url,
        data: "idProducto=" + idProducto,
        success: function (result) {
            console.log(result);
            var nuevaTabla = "";
            var subtotal = 0;
            var listaCarrito = result;
            for (var i = 0; i < listaCarrito.length; i++) {
//                subtotal += listaCarrito[i]['subTotal'];
//                nuevaTabla += "<tr>";
//                nuevaTabla += "<td id=\"indiceRow\">" + (i + 1) + "</td>";
//                nuevaTabla += "<td>" + listaCarrito[i]['nombres'] + "</td>";
//                nuevaTabla += "<td>" + listaCarrito[i]['descripcion'];
//                nuevaTabla += "<img src=\"ControladorImagenes?id=" + listaCarrito[i]['idProducto'] + "\" width=\"100\">";
//                nuevaTabla += "</td>";
//                nuevaTabla += "<td>" + listaCarrito[i]['cantidad'] + "</td>";
//                nuevaTabla += "<td>$ " + listaCarrito[i]['subTotal'].toFixed(2) + "</td>";
//                nuevaTabla += "<td>";
//                nuevaTabla += "<a class=\"btn btn-info btn-block\" href=\"#\">Editar</a>";
//                nuevaTabla += "<input type=\"hidden\" id=\"idProducto\" value=\"" + listaCarrito[i]['idProducto'] + "\">";
//                nuevaTabla += "<a class=\"btn btn-danger btn-block\" id=\"btnDelete\">Eliminar</a>";
//                nuevaTabla += "</td>";
//                nuevaTabla += "</tr>";
                subtotal += listaCarrito[i]['subTotal'];
                nuevaTabla += "<tr>";
                nuevaTabla += "<td id=\"indiceRow\">" + (i + 1) + "</td>";
                nuevaTabla += "<td>" + listaCarrito[i]['nombres'] + "</td>";
                nuevaTabla += "<td>" + listaCarrito[i]['descripcion'];
                nuevaTabla += "<img src=\"ControladorImagenes?id=" + listaCarrito[i]['idProducto'] + "\" width=\"100\">";
                nuevaTabla += "</td>";
                nuevaTabla += "<td>"
                nuevaTabla += "<input type=\"hidden\" id=\"idProductoCantidadChange\" value=\"listaCarrito[i]['idProducto']\">"
                nuevaTabla += "<input type=\"number\" id=\"inputCantidad\" value=\"listaCarrito[i]['cantidad']\" class=\"form-control text-center\" min=\"1\">"
                nuevaTabla += "</td>";
                nuevaTabla += "<td>$ " + listaCarrito[i]['subTotal'].toFixed(2) + "</td>";
                nuevaTabla += "<td>";
                nuevaTabla += "<a class=\"btn btn-info btn-block\" href=\"#\">Editar</a>";
                nuevaTabla += "<input type=\"hidden\" id=\"idProducto\" value=\"" + listaCarrito[i]['idProducto'] + "\">";
                nuevaTabla += "<a class=\"btn btn-danger btn-block\" id=\"btnDelete\">Eliminar</a>";
                nuevaTabla += "</td>";
                nuevaTabla += "</tr>";
            }
            subtotal = subtotal.toFixed(2);
            $("#bodyTablaCarrito").html(nuevaTabla);
            $("#subtotalCarrito").val(subtotal);
            $("#totalCarrito").val(subtotal);
            $("#seguirComprando").html("<i class=\"fas fa-cart-plus\">(<label style=\"color: orange\">" + listaCarrito.length + "</label>)</i> Seguir comprando");
            deleteItemButton();
            // mostrarCantidadArticulos();
        }, error: function (msg) {
            alert("Error en la peticion AJAX " + JSON.stringify(msg));
        }
    });
 * 
 * 
 */
/*
 *
function calcularNuevosTotales() {
    //Actualizamos subtotal y total de acuerdo a los valores de la lista de Carrito
    var url = "Controlador?accion=ListarCarrito";
    $.ajax({
        type: 'POST',
        url: url,
        success: function (result) {
            var subtotal = 0;
            var listaCarrito = result;
            console.log(listaCarrito);
            for (var i = 0; i < listaCarrito.length; i++) {
                subtotal += parseFloat(listaCarrito[i]['subTotal']);
            }
            $("#subtotalCarrito").val("$ " + subtotal.toFixed(2).replace('.', ','));
            $("#totalCarrito").val("$ " + subtotal.toFixed(2).replace('.', ','));
        }, error: function (msg) {
            alert("Todo salio mal en seguir comprando " + JSON.stringify(msg));
        }
    });
} 
 *

/*
public void listarImg(int id, HttpServletResponse response) throws IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = response.getOutputStream();
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_IMG_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Entramos");
                inputStream = resultSet.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;           
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Problemas con la consulta SQL o el Stream\n");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(preparedStatement);
            Conexion.close(resultSet);
            Conexion.close(connection);
        }
    }*/