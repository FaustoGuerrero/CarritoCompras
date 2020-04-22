/* global swal, listaCarrito */

window.onload = mostrarCantidadArticulos();

$(document).ready(function () {
    deleteItemButton();
    mostrarCantidadArticulos();
});

function deleteItemButton() {
    $("tr #btnDelete").click(function () {
        var idProducto = $(this).parent().find("#idProducto").val();
        swal({
            title: "Est\u00E1 seguro de eliminar?",
            text: "Una vez eliminado, debe volver a agregarlo si desea!",
            icon: "warning",
            buttons: true,
            dangerMode: true
        }).then((willDelete) => {
            if (willDelete) {
                eliminar(idProducto);
                swal("Tu registro ha sido eliminado!", {
                    icon: "success"
                });
            } else {
                swal("El registro no se elimin\u00F3!");
            }
        });
    });
}

function eliminar(idProducto) {
    var url = "Controlador?accion=Eliminar";
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
                subtotal += listaCarrito[i]['subTotal'];
                nuevaTabla += "<tr>";
                nuevaTabla += "<td id=\"indiceRow\">" + (i + 1) + "</td>";
                nuevaTabla += "<td>" + listaCarrito[i]['nombres'] + "</td>";
                nuevaTabla += "<td>" + listaCarrito[i]['descripcion'];
                nuevaTabla += "<img src=\"ControladorImagenes?id=" + listaCarrito[i]['idProducto'] + "\" width=\"100\">";
                nuevaTabla += "</td>";
                nuevaTabla += "<td>";
                nuevaTabla += "<input type=\"number\" id=\"inputCantidad\" value=\"" + listaCarrito[i]['cantidad'] + "\" class=\"form-control text-center\" min=\"1\" onclick=\"changeCantidad(" + listaCarrito[i]['idProducto'] + ")\">";
                nuevaTabla += "</td>";
                nuevaTabla += "<td>$ " + listaCarrito[i]['subTotal'].toFixed(2) + "</td>";
                nuevaTabla += "<td>";
                nuevaTabla += "<a class=\"btn btn-info btn-block\" href=\"#\">Editar</a>";
                nuevaTabla += "<input type=\"hidden\" id=\"idProducto\" value=\"" + listaCarrito[i]['idProducto'] + "\">";
                nuevaTabla += "<a class=\"btn btn-danger btn-block\" id=\"btnDelete\">Eliminar</a>";
                nuevaTabla += "</td>";
                nuevaTabla += "</tr>";
            }       
            subtotal = subtotal.toFixed(2).replace('.', ',');
            $("#bodyTablaCarrito").html(nuevaTabla);
            $("#subtotalCarrito").val("$ " + subtotal);
            $("#totalCarrito").val("$ " + subtotal);
            $("#seguirComprando").html("<i class=\"fas fa-cart-plus\">(<label style=\"color: orange\">" + listaCarrito.length + "</label>)</i> Seguir comprando");
            deleteItemButton();
        }, error: function (msg) {
            alert("Error en la peticion AJAX " + JSON.stringify(msg));
        }
    });
}

function changeCantidad(idProducto) {
    var table = document.getElementById("tablaDatos"), rIndex, cantidad, total, nuevoSubtotalProducto, listaCarrito;
    for (var i = 1; i < table.rows.length; i++) {
        table.rows[i].onclick = function () {
            rIndex = this.rowIndex;
            cantidad = this.cells[3].querySelector('input').value;
            var url = "Controlador?accion=ActualizarCantidad";
            $.ajax({
                type: 'POST',
                url: url,
                data: "idProducto=" + idProducto + "&Cantidad=" + cantidad,
                success: function (result) {
                    listaCarrito = result;
                    console.log(listaCarrito);
                }, error: function (msg) {
                    alert("Todo salio mal en el Ajax del change " + JSON.stringify(msg));
                }
            }).done(function () {
                total = 0;
                for (var i = 0; i < listaCarrito.length; i++) {
                    if (listaCarrito[i]['idProducto'] === idProducto) {
                        nuevoSubtotalProducto = "$" + (listaCarrito[i]['subTotal']).toFixed(2).replace('.', ',');
                    }                    
                    total += parseFloat(listaCarrito[i]['subTotal']);                                        
                }                
                var tabla = document.getElementById("tablaDatos");
                tabla.rows[rIndex].cells[4].innerHTML = nuevoSubtotalProducto;
                $("#subtotalCarrito").val("$ " + total.toFixed(2).replace('.', ','));
                $("#totalCarrito").val("$ " + total.toFixed(2).replace('.', ','));
            });
        };
    }
}

//Para mostrar la cantidad de articulos en Carrito.jsp
function mostrarCantidadArticulos() {
    var url = "Controlador?accion=Listar";
    $.ajax({
        type: 'POST',
        url: url,
        success: function (result) {
            console.log(result);
            $("#seguirComprando").html("<i class=\"fas fa-cart-plus\">(<label style=\"color: orange\">" + result + "</label>)</i> Seguir comprando");
        }, error: function (msg) {
            alert("Todo salio mal en seguir comprando " + JSON.stringify(msg));
        }
    });
}



