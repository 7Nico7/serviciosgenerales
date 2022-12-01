$(document).ready(function () {

//SE genera la Tabla 
    var table = $('#mantenimiento').DataTable({
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
                    //Columnas que se exportan al archivo
                    exportOptions: {
                        columns: [0, 1, 2, 3, 4, 5, 6]
                    }
                },
                {
                    //definimos estilos del boton PDF
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
                        columns: [0, 1, 2, 3, 4, 5, 6]
                    },
                    customize: function (doc) {
                        doc.content[1].margin = [100, 0, 100, 0];
                    }
                },
                {
                    //definimos estilos del boton de Imprimir
                    extend: "print",
                    text: 'Imprimir',
                    className: 'btn btn-sm btn-info',
                    filename: titulo,
                    title: function () {
                        var searchString = table.search();
                        return searchString.length ? "Search: " + searchString : titulo;
                    },
                    exportOptions: {
                        columns: [0, 1, 2, 3, 4, 5, 6]
                    }
                }
            ]
        },
        ajax: {
            url: 'ControllerTablas?tabla=' + tipo + '&Modulo=Mantenimiento',
            dataSrc: ''
        },
        //Datos de la Tabla
        columns: [
            {data: 'cve_bienes'},
            {data: 'cve_mantenimiento'},
            {data: 'tipo_bien'},
            {data: 'num_inventario'},
            {data: 'fecha_manteni'},
            {data: 'proximo_mantenimiento'},
            {data: 'dias_restantes'},
            {defaultContent: " <button class='btn btn-sm btn-info btnEditar'><span class='material-icons'>edit</span></button>\n\
                    <button  class='btnBorrar btn btn-primary btn-sm'><span class='material-icons'>delete</span></button>\n\
                    <button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>smart_display</span></button>\n\
                <button class='btn btn-primary btn-sm btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>\n\
                    <button class='btn btn btn-primary btn-sm btnInfo'><span class='material-icons'>feed</span></button>"},
            {data: 'nombre_archivo'},
            {data: 'evidenciaVideo'},
            {data: 'nombre_archivo_reporte'},
            {data: 'evidenciaReporte'}
        ]
    });

    //boton editar
    $("#mantenimiento tbody").on('click', '.btnEditar', function () {
        let data = table.row($(this).parents()).data();
        var cve_bienes = data.cve_bienes;
        var cve_mantenimiento = data.cve_mantenimiento; //capturo el ID 
        var tipo_bien = data.tipo_bien;
        var num_inventario = data.num_inventario;

        location.href = 'ControllerEditar?Editar=Mantenimiento&cve_mantenimiento=' + cve_mantenimiento
                + "&tipo_bien=" + tipo_bien + "&inventario=" + num_inventario + "&cve_bienes=" + cve_bienes;
    });


    //boton Información Detallada del Mantenimiento
    $("#mantenimiento tbody").on('click', '.btnInfo', function () {
        let data = table.row($(this).parents()).data();
        var cve_bienes = data.cve_bienes;
        var cve_mantenimiento = data.cve_mantenimiento; //capturo el ID 
        var tipo_bien = data.tipo_bien;
        var num_inventario = data.num_inventario;
        var fecha_manteni = data.fecha_manteni;
        var proximo_mantenimiento = data.proximo_mantenimiento;
        var dias_restantes = data.dias_restantes;
        location.href = 'ControllerBienes?selecciono=detalleMantenimiento&cve_mantenimiento=' + cve_mantenimiento
                + "&tipo_bien=" + tipo_bien + "&inventario=" + num_inventario + "&cve_bienes=" + cve_bienes +
                "&fecha_manteni=" + fecha_manteni + "&proximo_mantenimiento=" + proximo_mantenimiento +
                "&dias_restantes=" + dias_restantes;
    });


    //boton para mostrar la Evidencia
    $("#mantenimiento tbody").on('click', '.btnEvidencia', function () {
        let data = table.row($(this).parents()).data();
        var video = data.nombre_archivo; //capturo el ID 

        if (video === " " || video === null) {
            video = "nulo";
        }

        window.open('pages/evidencia.jsp?video=' + video, '_blank');
    });

    //boton para mostrar Reporte de evidencia
    $("#mantenimiento tbody").on('click', '.btnEvidencia2', function () {
        let data = table.row($(this).parents()).data();
        var pdf = data.nombre_archivo_reporte; //capturo el ID 
        if (pdf === " " || pdf === null) {
            pdf = "nulo";
        }
        window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
    });

    //btoton borrar 
    $("#mantenimiento tbody").on('click', '.btnBorrar', function () {
// e.preventDefault(); //evita la carga total de la apagina
        let data = table.row($(this).parents()).data();
        var cve_mantenimiento = data.cve_mantenimiento; //capturo el ID 
        var evidencia = data.evidenciaVideo;
        var reporte = data.evidenciaReporte;
        var eliminar = confirm('Eliminar Registro ');
        if (eliminar) {
            var datos = 'cve_mantenimiento=' + cve_mantenimiento + "&archivo=" + evidencia + "&reporte=" + reporte;
            $.ajax({
                url: "ControllerEliminar?Eliminar=mantenimiento", type: 'POST', data: datos,
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
    //OCULTAR FILAS
    const fila_nombreArchivo = table.column(8);
    //Se oculta la fila
    fila_nombreArchivo.visible(!fila_nombreArchivo.visible()); // true or false

    const fila_rutaArchivo = table.column(9);
    fila_rutaArchivo.visible(!fila_rutaArchivo.visible());


    const fila_nombreArchivo2 = table.column(10);
    //Se oculta la fila
    fila_nombreArchivo2.visible(!fila_nombreArchivo2.visible()); // true or false

    const fila_rutaArchivo2 = table.column(11);
    fila_rutaArchivo2.visible(!fila_rutaArchivo2.visible());

    const fila_No_mantenimiento = table.column(0);
    fila_No_mantenimiento.visible(!fila_No_mantenimiento.visible());

});