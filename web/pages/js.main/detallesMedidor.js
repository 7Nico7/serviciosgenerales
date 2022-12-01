
$(document).ready(function () {
//SE genera la Tabla 
    var table = $('#DataTableMedidor').DataTable({
        responsive: true,
        order: [[9, 'desc']],
        pageLength: 1,
        lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todos']],
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
                        columns: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
                    }
                },
                {
                    //definimos estilos del boton de pdf
                    extend: "pdf",
                    text: 'Exportar a pdf',
                    className: 'btn btn-sm btn-danger',
                    filename: 'Detalle Recibo',
                    download: 'open',
                    title: function () {
                        var searchString = table.search();
                        return searchString.length ? "Search: " + searchString : titulo;
                    },
                    exportOptions: {
                        columns: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
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
                        columns: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
                    }
                }
            ]
        },
        ajax: {
            url: 'ControllerTablas?Modulo=DetalleMedidor&cve_medidor=' + data,
            dataSrc: ''
        },
        //Valida si la lista es extintores para eliminar
        columns:
                [
                    {data: 'cve_medidor'},
                    {data: 'medidor'},
                    {data: 'servicio'},
                    {data: 'planta'},
                    {data: 'partida'},
                    {data: 'local'},
                    {data: 'year'},
                    {data: 'periodo'},
                    {data: 'periodoInicio'},
                    {data: 'periodoFinal'},
                    {data: 'consumo'},
                    {data: 'saldo'},
                    {data: 'nombre_archivo'},
                    {data: 'archivo'},
                    {data: 'nombre_archivoReporte'},
                    {data: 'archivo_reporte'},
                    {defaultContent: "<button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>picture_as_pdf</span></button>\n\
        <button class='btn btn-sm btn-primary btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>"}
                ]
    });
    //OCULTAR FILAS
    const fila_year = table.column(6);
    //Se oculta la fila
    fila_year.visible(!fila_year.visible()); // true or false

    const fila_periodo = table.column(7);
    //Se oculta la fila
    fila_periodo.visible(!fila_periodo.visible()); // true or false

    const fila_inicio = table.column(8);
    //Se oculta la fila
    fila_inicio.visible(!fila_inicio.visible()); // true or false

    const fila_periodofinal = table.column(9);
    //Se oculta la fila
    fila_periodofinal.visible(!fila_periodofinal.visible()); // true or false

    const fila_consumo = table.column(10);
    //Se oculta la fila
    fila_consumo.visible(!fila_consumo.visible()); // true or false

    const fila_saldo = table.column(11);
    //Se oculta la fila
    fila_saldo.visible(!fila_saldo.visible()); // true or false


    const fila_nombreArchivo = table.column(12);
    //Se oculta la fila
    fila_nombreArchivo.visible(!fila_nombreArchivo.visible()); // true or false

    const fila_archivo = table.column(13);
    //Se oculta la fila
    fila_archivo.visible(!fila_archivo.visible()); // true or false

    const fila_nombreReporte = table.column(14);
    //Se oculta la fila
    fila_nombreReporte.visible(!fila_nombreReporte.visible()); // true or false

    const fila_reporte = table.column(15);
    //Se oculta la fila
    fila_reporte.visible(!fila_reporte.visible()); // true or false


    //boton para mostrar Recibo
    $("#DataTableMedidor tbody").on('click', '.btnEvidencia', function () {
        let data = table.row($(this).parents()).data();
        var pdf = data.nombre_archivo; //capturo el ID 
        if (pdf === " " || pdf === null) {
            pdf = "nulo";
        }
        window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
    });

    //boton para mostrar Reporte
    $("#DataTableMedidor tbody").on('click', '.btnEvidencia2', function () {
        let data = table.row($(this).parents()).data();
        var pdf = data.nombre_archivoReporte; //capturo el ID 
        if (pdf === " " || pdf === null || pdf === undefined || pdf.trim().length === 0) {
            pdf = "nulo";
        }
        window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
    });
});
$(document).ready(function () {
//SE genera la Tabla 
    var table = $('#DataTableRecibos').DataTable({
        responsive: true,
        order: [[3, 'desc']],
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
                        columns: [0, 1, 2, 3, 4, 5, 6]
                    }
                },
                {
                    //definimos estilos del boton de pdf
                    extend: "pdf",
                    text: 'Exportar a pdf',
                    className: 'btn btn-sm btn-danger',
                    filename: 'Detalle Recibo',
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
                        columns: [0, 1, 2, 3, 4, 5, 6]
                    }
                }
            ]
        },
        ajax: {
            url: 'ControllerTablas?Modulo=DetalleMedidor&cve_medidor=' + data + "&tablaDetalleMedidorRecibos=1",
            dataSrc: ''
        },
        //Valida si la lista es extintores para eliminar
        columns:
                [
                    {data: 'cve_recibo'},
                    {data: 'year'},
                    {data: 'periodo'},
                    {data: 'periodoInicio'},
                    {data: 'periodoFinal'},
                    {data: 'consumo'},
                    {data: 'saldo'},
                    {data: 'nombre_archivo'},
                    {data: 'archivo'},
                    {data: 'nombre_archivoReporte'},
                    {data: 'archivo_reporte'},
                    {defaultContent: "<button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>picture_as_pdf</span></button>\n\
                    <button class='btn btn-primary btn-sm btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>"}
                ]
    });

    const fila_nombre = table.column(8);
    //Se oculta la fila
    fila_nombre.visible(!fila_nombre.visible()); // true or false
    
    const fila_reporte = table.column(10);
    //Se oculta la fila
    fila_reporte.visible(!fila_reporte.visible()); // true or false

    //boton para mostrar Recibo
    $("#DataTableRecibos tbody").on('click', '.btnEvidencia', function () {
        let data = table.row($(this).parents()).data();
        var pdf = data.nombre_archivo; //capturo el ID 
        if (pdf === " " || pdf === null) {
            pdf = "nulo";
        }
        window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
    });

    //boton para mostrar Recibo
    $("#DataTableRecibos tbody").on('click', '.btnEvidencia2', function () {
        let data = table.row($(this).parents()).data();
        var pdf = data.nombre_archivoReporte; //capturo el ID 
        if (pdf === " " || pdf === null || pdf.trim().length === 0 || pdf === undefined) {
            pdf = "nulo";
        }
        window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');
    });
});


