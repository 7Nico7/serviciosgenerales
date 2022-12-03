var titulo = "Lista de Medidores";
//SE genera la Tabla 
var table = $('#Medidor').DataTable({
    responsive: true,
    language: {
        url: "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
    },
    dom: "Bfrtip",
    buttons: {
        dom: {
            button: {
                className: 'btn'
            }
        },
        buttons: [
            {
                //definimos estilos del boton de excel
                extend: "excel",
                text: 'Exportar a Excel',
                className: 'btn btn-sm btn-success',
                filename: titulo,
                // configuración insertCells
                insertCells: [
                    {
                        cells: '1', // Target cell B3
                        content: titulo // without pushCol or pushRow defined, the cell
                    }
                ],
                excelStyles: [
                    {// Add an excelStyles definition
                        cells: "2", // adonde se aplicaran los estilos (fila 2)
                        style: {// The style block
                            font: {// Style the font
                                name: "Arial", // Font name
                                size: "14", // Font size
                                color: "FFFFFF", // Font Color

                                b: true // negrita SI
                            },
                            fill: {// Estilo de relleno (background)
                                pattern: {// tipo de rellero (pattern or gradient)
                                    color: "b38e5d" // color de fondo de la fila
                                }
                            }
                        }
                    },
                    {// Add an excelStyles definition
                        cells: "1", // adonde se aplicaran los estilos (fila 2)
                        style: {// The style block
                            font: {// Style the font
                                name: "Arial", // Font name
                                size: "14", // Font size
                                color: "FFFFFF", // Font Color

                                b: true // negrita SI
                            },
                            fill: {// Estilo de relleno (background)
                                pattern: {// tipo de rellero (pattern or gradient)
                                    color: "9D2449" // color de fondo de la fila
                                }
                            },
                            // Alignment Object
                            alignment: {
                                vertical: "center",
                                horizontal: "center"
                                        //wrapText: true
                            }
                        }
                    }
                ],
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5]
                }
            },
            {
                //definimos estilos del boton de excel
                extend: "pdf",
                text: 'Exportar a pdf',
                className: 'btn btn-sm btn-danger',
                filename: titulo,
                download: 'open',
                title: function () {
                    var searchString = table.search();
                    return searchString.length ? "Search: " + searchString : titulo;
                },
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5]
                },
                customize: function (doc) {
                    doc.content[1].margin = [100, 0, 100, 0];
                }
            },
            {
                //definimos estilos del boton de excel
                extend: "print",
                text: 'Imprimir',
                className: 'btn btn-sm btn-info',
                filename: titulo,
                title: function () {
                    var searchString = table.search();
                    return searchString.length ? "Search: " + searchString : titulo;
                },
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5]
                }
            }
        ]
    },
    ajax: {
        url: 'ControllerTablas?Modulo=Medidor',
        dataSrc: ''
    },
    //Valida si la lista es extintores para eliminar
    columns: [
        {data: 'cve_medidor'},
        {data: 'partida'},
        {data: 'planta'},
        {data: 'local'},
        {data: 'medidor'},
        {data: 'servicio'},
        {defaultContent: " <button class='btn btn-sm btn-info btnEditar'><span class='material-icons'>edit</span></button>\n\
                    <button  class='btnBorrar btn btn-primary btn-sm'><span class='material-icons'>delete</span></button>\n\
                    <button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>picture_as_pdf</span></button>\n\
\n\                 <button class='btn btn-sm btn-primary btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>\n\
                    <button class='btn btn btn-primary btn-sm btnInfo'><span class='material-icons'>feed</span></button>"}
    ]
});


//Editar Medidor
$("#Medidor tbody").on('click', '.btnEditar', function () {
    let data = table.row($(this).parents()).data();
    var cve_medidor = data.cve_medidor; //capturo el ID 
    location.href = "ControllerEditar?Editar=Medidor" + "&cve_medidor=" + cve_medidor;
});


//Editar detalle
$("#Medidor tbody").on('click', '.btnInfo ', function () {
    let data = table.row($(this).parents()).data();
    var cve_medidor = data.cve_medidor; //capturo el ID 
    location.href = "ControllerCFE?pagina=detalleMedidor" + "&cve_medidor=" + cve_medidor;
});

//btoton Evidencia 
$("#Medidor tbody").on('click', '.btnEvidencia', function () {
    let data = table.row($(this).parents()).data();
    evidencia("recibo", data);
});

$("#Medidor tbody").on('click', '.btnEvidencia2', function () {
    let data = table.row($(this).parents()).data();
    evidencia("reporte", data);
});


function evidencia(evid, data1) {
    // e.preventDefault(); //evita la carga total de la apagina

    var cve_medidor = data1.cve_medidor; //capturo el ID 
    var pdf = "";
    var datos = 'cve_medidor=' + cve_medidor;

    $.ajax({
        type: 'GET',
        url: 'ControllerEvidencia?pagina=recibo&' + datos,
        dataType: 'json',
        success: function (data) {
            
            var r = data.nombre_archivo;
            var re = data.nombre_archivoReporte;

            if (data.cve_medidor !== 0 && r.trim().length !== 0 && r !== undefined ) {
                if (evid === "recibo") {
                    pdf =data.nombre_archivo;
                }
                if (evid === "reporte") {
                   pdf =  data.nombre_archivoReporte;
                }
            } else {
                pdf = "nulo";
            }
            window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
        }
    });

}


//btoton borrar 
$("#Medidor tbody").on('click', '.btnBorrar', function () {
// e.preventDefault(); //evita la carga total de la apagina
    let data = table.row($(this).parents()).data();
    var cve_medidor = data.cve_medidor; //capturo el ID 

    var eliminar = confirm('Eliminar Registro ');
    if (eliminar) {
        var datos = 'cve_medidor=' + cve_medidor;
        $.ajax({
            url: "ControllerEliminar?Eliminar=Medidor", type: 'POST', data: datos,
            success: function (data) {
                if (data) {
                    console.log(data.trim());
                    if (data.trim() === "nulo") {
                        swal('Error!', 'El Parametro Eliminar es Nulo', 'error');
                    } else if (data.trim() === "desconocido") {
                        swal('Error!', 'El Parametro Eliminar Es Desconocido', 'error');
                    } else if (data.trim() === "eliminado") {
                        swal('Mensaje del sistema', 'Se Elimino El Registro', 'success');
                    } else {
                        swal('Error!', 'Ocurrio un erro al eliminar', 'error');
                    }

                }
                table.ajax.reload(null, false);
            }
        });
    }
});