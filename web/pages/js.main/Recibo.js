

var titulo = "Lista de Medidores";
//SE genera la Tabla 
var table = $('#Recibo').DataTable({
    language: {
        url: "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
    },
    responsive: true,
    order: [[0, 'desc']],

    dom: "Bfrtip",
    buttons: {
        dom: {
            button: {
                className: 'btn'
            }
        },
        buttons: [

            {
                extend: 'searchBuilder',
                config: {
                    depthLimit: 2
                }
            },
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
        url: 'ControllerTablas?Modulo=Recibo&Tipo=todos',
        dataSrc: ''
    },
    //Valida si la lista es extintores para eliminar
    columns: [
        {data: 'periodoInicio'},
        {data: 'cve_recibo'},
        {data: 'cve_medidor'},
        {data: 'year'},
        {data: 'periodo'},
        {data: 'consumo'},
        {data: 'saldo'},
        {data: 'archivo'},
        {data: 'nombre_archivo'},
        {data: 'archivo_reporte'},
        {data: 'nombre_archivoReporte'},
        {defaultContent: " <button class='btn btn-sm btn-info btnEditar'><span class='material-icons'>edit</span></button>\n\
                    <button  class='btn btn-primary btn-sm btnBorrar'><span class='material-icons'>delete</span></button>\n\
                    <button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>picture_as_pdf</span></button>\n\
                    <button class='btn btn-primary btn-sm btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>\n\
                    <button class='btn btn-primary btn-sm btnInfo'><span class='material-icons'>feed</span></button>"}
    ]
});

//OCULTAR FILAS
const fila_periodoInicio = table.column(0);
//Se oculta la fila
fila_periodoInicio.visible(!fila_periodoInicio.visible());

const fila_rutaArchivo = table.column(7);
//Se oculta la fila
fila_rutaArchivo.visible(!fila_rutaArchivo.visible());

const fila_nombreArchivo = table.column(8);
//Se oculta la fila
fila_nombreArchivo.visible(!fila_nombreArchivo.visible()); // true or false


const fila_rutaReporte = table.column(9);
//Se oculta la fila
fila_rutaReporte.visible(!fila_rutaReporte.visible()); // true or false

const fila_nombreReporte = table.column(10);
//Se oculta la fila
fila_nombreReporte.visible(!fila_nombreReporte.visible()); // true or false


//btoton borrar 
$("#Recibo tbody").on('click', '.btnBorrar', function (e) {
    // e.preventDefault(); //evita la carga total de la apagina
    let data = table.row($(this).parents()).data();
    var cve_recibo = data.cve_recibo; //capturo el ID 
    var archivo = data.archivo;
    var archivoR = data.archivo_reporte;
    var eliminar = confirm('Eliminar Recibo ');
    if (eliminar) {
        var datos = 'cve_recibo=' + cve_recibo + "&archivo=" + archivo + "&archivo_reporte=" + archivoR;
        $.ajax({
            url: "ControllerEliminar?Eliminar=Recibo", type: 'POST', data: datos,
            success: function (data) {

                if (data) {
                    if (data.trim() === "nulo") {
                        swal('Error!', 'El Parametro Eliminar es Nulo', 'error');
                    } else if (data.trim() === "desconocido") {
                        swal('Error!', 'El Parametro Eliminar Es Desconocido', 'error');
                    }
                    if (data.trim() === "eliminado") {
                        swal('Mensaje del sistema', 'Se Elimino El Registro', 'success');
                    }
                    if (data.trim() === "incorrecto") {
                        swal('Error!', 'El Numero de Recibo es Incorrecto', 'error');
                    }
                } else {
                    swal('Error!', 'Ocurrio un erro al eliminar', 'error');
                }


                table.ajax.reload(null, false);
            }
        });
    }
});


//boton para mostrar Recibo
$("#Recibo tbody").on('click', '.btnEvidencia', function () {
    let data = table.row($(this).parents()).data();
    var pdf = data.nombre_archivo; //capturo el ID 
    if (pdf === " " || pdf === null) {
        pdf = "nulo";
    }
    window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
});

//boton para mostrar Reporte
$("#Recibo tbody").on('click', '.btnEvidencia2', function () {
    var data = table.row($(this).parents()).data();
    var pdf = data.nombre_archivoReporte; //capturo el ID 
    if (pdf === " " || pdf === null || pdf === undefined) {
        pdf = "nulo";
    }
    window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
});


//boton Editar
$("#Recibo tbody").on('click', '.btnEditar', function () {
    let data = table.row($(this).parents()).data();
    var cve_recibo = data.cve_recibo; //capturo el ID 
    //Se envia a un Formulario
    location.href = "ControllerEditar?Editar=Recibo" + "&cve_recibo=" + cve_recibo;
});

//boton Detalles
$("#Recibo tbody").on('click', '.btnInfo', function () {
    let data = table.row($(this).parents()).data();
    var cve_recibo = data.cve_recibo; //capturo el ID 
    //Se envia a un Formulario
    location.href = "ControllerCFE?pagina=detalleRecibo" + "&cve_recibo=" + cve_recibo;
});