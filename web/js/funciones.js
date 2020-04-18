/* global swal */

$(document).ready(function () {
    $("tr #btnDelete").click(function () {
        var idProducto = $(this).parent().find("#idProducto").val();
        console.log("entramos");
        console.log($(this).parent().find("#indiceRow").val());
        console.log(document.getElementById("tablaDatos").deleteRow("#indiceRow"));
        swal({
            title: "Est\u00E1 seguro de eliminar?",
            text: "Una vez eliminado, debe volver a agregarlo si desea!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
            if (willDelete) {
                eliminar(idProducto);
//                console.log($(this).parent().find("#indiceRow").val());
//                console.log(document.getElementById("tablaDatos").deleteRow("#indiceRow"));
                swal("Tu registro ha sido eliminado!", {
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        //aqui debemos de actualizar la tabla
                       // parent.location.href = "Controlador?accion=Carrito"
                    }
                });
            } else {
                swal("El registro no se elimin\u00F3!");
            }
        });
        console.log("final del click");
    });
    console.log("fuera del evento click");

    function eliminar(idProducto) {
        var url = "Controlador?accion=Eliminar";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idProducto=" + idProducto,
            success: function (data, textStatus, jqXHR) {
               // alert("Todo salio bien");
            },error:function(msg) {
                alert("Todo salio mal");
            }            
        })
    }
});

