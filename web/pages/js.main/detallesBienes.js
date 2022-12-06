
console.log(bien);
var datosTabla;
var nombre;
if (bien.tipo_bien === "Climas") {
    datosTabla = [bien.unidad, bien.cve_bienes, bien.tipo_bien, bien.num_inventario, bien.marca, bien.modelo, bien.serie, bien.fecha_factura,
        bien.fecha_instalacion, bien.departamento, bien.ubic_departamento, bien.responsable, bien.dependencia, bien.area, bien.ubicaObservaciones, bien.status];
    nombre = ["Unidad", "No.Bien", "Tipo de Bien", "Inventario", "Marca", "Modelo", "Serie", "Fecha_factura", "Fecha de Instalación", "Departemento",
        "Ubicación del departemento", "Responsable", "Dependencia", "Área", "Observaciones de Ubicación", "Estado"];
}

if (bien.tipo_bien === "Extintores") {
    datosTabla = [bien.unidad, bien.cve_bienes, bien.tipo_bien, bien.num_inventario, bien.marca, bien.modelo, bien.serie,
        bien.ubic_departamento, bien.responsable, bien.status];
    nombre = ["Unidad", "No.Bien", "Tipo de Bien", "Inventario", "Marca", "Modelo", "Serie", "Ubicación del departemento",
        "Responsable", "Estado"];
}

if (bien.tipo_bien === "Impresoras") {
    datosTabla = [bien.cve_bienes, bien.tipo_bien, bien.num_inventario, bien.marca, bien.modelo, bien.serie,
        bien.ubic_departamento, bien.tonner, bien.responsable, bien.status];
    nombre = ["No.Bien", "Tipo de Bien", "Inventario", "Marca", "Modelo", "Serie", "Ubicación del departemento", "Tornners",
        "Responsable", "Estado"];
}


var content = " <table id='infoBienes' class='table'>";
var cont = 0;
for (var i = 0; i < (datosTabla.length / 2); i++) {

    content += "<tr><th>" + nombre[cont] + "</th>" +
            "<td>" + datosTabla[cont] + "</td>";
    cont++;
    content += "<th>" + nombre[cont] + "</th>" +
            "<td>" + datosTabla[cont] + "</td>" + "</tr>";
    cont++;
}
content += "</table>";
//document.getElementById('generaTabla').innerHTML = content;

//------------------------------------------
$(function () {
    $("#Excel").click(function (e) {
        var table = $("#infoBienes");
        if (table && table.length) {
            $(table).table2excel({
                exclude: ".noExl",
                name: "Excel Document Name",
                filename: "${bien.tipo_bien}" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
                fileext: ".xls",
                exclude_img: true,
                exclude_links: true,
                exclude_inputs: true,
                preserveColors: true
            });
        }
    });
});
$("#crear").click(function () {
    var doc = new jsPDF();
    // var imgData = new Image();
    // imgData.src = "imagen/SAIG.jpeg";
    // doc.addImage(imgData, 20, 40, 100, 100);

    doc.setFontSize(30);
    doc.setTextColor(255, 0, 0);
    doc.text(40, 20, "SMAIG");
    doc.setFontSize(14);
    doc.setTextColor(0, 0, 0);
    doc.text(20, 30, "Bien numero ");
    // doc.addImage(imgData, 'JPEG', 10, 40, 180, 180);
    // imgData.onload = function () {
    doc.save("mipdf.pdf");
    //};
});

var tipo = bien.tipo_bien;



$(document).ready(function () {

    var titulo1 = "Datos del Bien No." + bien.cve_bienes;


    var table1 = $('#Bien').DataTable({
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
                    filename: titulo1,
                    // configuración insertCells
                    insertCells: [
                        {
                            cells: '1', // Target cell B3
                            content: titulo1 // without pushCol or pushRow defined, the cell
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
                        columns: (bien.tipo_bien === 'Extintores') ? [0, 1, 2, 4, 5] : (bien.tipo_bien === 'Impresoras')
                                ? [0, 1, 2, 4, 5, 10] : [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
                    }
                },
                {
                    //definimos estilos del boton de excel
                    extend: "pdf",
                    text: 'Exportar a pdf',
                    className: 'btn btn-sm btn-danger',
                    filename: titulo1,
                    orientation: 'landscape',
                    download: 'open',
                    title: function () {
                        var searchString = table1.search();
                        return searchString.length ? "Search: " + searchString : titulo1;
                    },
                    exportOptions: {
                        columns: (bien.tipo_bien === 'Extintores') ? [0, 1, 2, 4, 5] : (bien.tipo_bien === 'Impresoras')
                                ? [0, 1, 2, 4, 5, 10] : [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13]
                    }
                },
                {
                    //definimos estilos del boton de excel
                    extend: "print",
                    text: 'Imprimir',
                    className: 'btn btn-sm btn-info',
                    filename: titulo1,
                    title: function () {
                        var searchString = table1.search();
                        return searchString.length ? "Search: " + searchString : titulo1;
                    },
                    exportOptions: {
                        columns: (bien.tipo_bien === 'Extintores') ? [0, 1, 2, 4, 5] : (bien.tipo_bien === 'Impresoras')
                                ? [0, 1, 2, 4, 5, 10] : [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 13]
                    }
                }
            ]
        },
        ajax: {
            url: 'ControllerTablas?Modulo=DetalleBienes1&cve_bienes=' + bien.cve_bienes + '&tipo=' + bien.tipo_bien,
            dataSrc: ''
        },
        //Valida si la lista es extintores para eliminars
        columns:
                [
                    {data: 'cve_bienes'},
                    {data: 'tipo_bien'},
                    {data: 'num_inventario'},
                    {data: 'responsable'},
                    {data: 'status'},
                    {data: 'ubic_departamento'},
                    {data: 'dependencia'},
                    {data: 'departamento'},
                    {data: 'ubicaObservaciones'},
                    {data: 'area'},
                    {data: 'serie'},
                    {data: 'unidad'},
                    {data: 'fecha_factura'},
                    {data: 'fecha_instalacion'},
                    {defaultContent: " <button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>smart_display</span></button>\n\
                                        <button class='btn btn-sm btn-primary btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>"}
                ]
    });

    //btoton Evidencia 
    $("#Bien tbody").on('click', '.btnEvidencia', function () {
        let data = table1.row($(this).parents()).data();
        evidencia("video", data);
    });

    $("#Bien tbody").on('click', '.btnEvidencia2', function () {
        let data = table1.row($(this).parents()).data();
        evidencia("reporte", data);
    });


    function evidencia(evid, data1) {
        // e.preventDefault(); //evita la carga total de la apagina

        var cve_bienes = data1.cve_bienes; //capturo el ID 
        var pdf = "";
        var video = "";
        var datos = 'cve_bienes=' + cve_bienes;

        $.ajax({
            type: 'GET',
            url: 'ControllerEvidencia?pagina=bienes&' + datos,
            dataType: 'json',
            success: function (data) {
                if (data.cve_bienes !== 0) {
                    if (evid === "video") {
                        video = data.nombre_archivo;

                        if (video !== undefined && video !== " " && video !== "" && video !== null && video.trim().length !== 0) {
                            window.open('pages/evidencia.jsp?video=' + video, '_blank');
                        } else {
                            window.open('pages/evidencia.jsp?video=nulo', '_blank');
                        }

                    }

                    if (evid === "reporte") {
                        pdf = data.nombre_archivo_reporte;

                        if (pdf !== undefined && pdf !== " " && pdf !== "" && pdf !== null && pdf.trim().length !== 0) {
                            window.open('pages/evidencia.jsp?pdf=' + pdf, '_blank');

                        } else {
                            window.open('pages/evidencia.jsp?pdf=nulo', '_blank');
                        }
                    }
                } else {
                    if (evid === "video") {

                        window.open('pages/evidencia.jsp?video=' + "nulo", '_blank');
                    }
                    if (evid === "reporte") {
                        window.open('pages/evidencia.jsp?pdf=' + "nulo", '_blank');
                    }
                }



            }
        });

    }

//OCULTAR FILAS
    const fila_dependencia = table1.column(6);
    const fila_departamento = table1.column(7);
    const fila_UbicacionObserva = table1.column(8);
    const fila_area = table1.column(9);
    const fila_serie = table1.column(10);
    const fila_unidad = table1.column(11);
    const fila_fecha_fac = table1.column(12);
    const fila_fecha_insta = table1.column(13);


    if (bien.tipo_bien === "Extintores") {
        fila_dependencia.visible(!fila_dependencia.visible());
        fila_departamento.visible(!fila_departamento.visible());
        fila_UbicacionObserva.visible(!fila_UbicacionObserva.visible());
        fila_area.visible(!fila_area.visible());
        fila_serie.visible(!fila_serie.visible());
        fila_unidad.visible(!fila_unidad.visible());
        fila_fecha_fac.visible(!fila_fecha_fac.visible());
        fila_fecha_insta.visible(!fila_fecha_insta.visible());

    }

    if (bien.tipo_bien === "Impresoras") {
        fila_dependencia.visible(!fila_dependencia.visible());
        fila_departamento.visible(!fila_departamento.visible());
        fila_UbicacionObserva.visible(!fila_UbicacionObserva.visible());
        fila_area.visible(!fila_area.visible());
        fila_unidad.visible(!fila_unidad.visible());
        fila_fecha_fac.visible(!fila_fecha_fac.visible());
        fila_fecha_insta.visible(!fila_fecha_insta.visible());

    }







    var titulo = "Mantenimintos del Bien No." + bien.cve_bienes;

    /*----------------------------DataTable----------------------------------*/

//SE genera la Tabla 
    var table = $('#DetallesBienes').DataTable({
        responsive: true,
        order: [[5, 'asc']],
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
                        columns: (bien.tipo_bien === "Extintores") ? [0, 1, 2, 4, 5, 6, 7, 9] : (bien.tipo_bien === "Impresoras") ?
                                [0, 1, 2, 4, 5, 6, 7, 8, 9] : [0, 1, 2, 3, 4, 5, 6, 7, 9]
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
                        columns: (bien.tipo_bien === "Extintores") ? [0, 1, 2, 4, 5, 6, 7, 9] : (bien.tipo_bien === "Impresoras") ?
                                [0, 1, 2, 4, 5, 6, 7, 8, 9] : [0, 1, 2, 3, 4, 5, 6, 7, 9]
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
                        columns: (bien.tipo_bien === "Extintores") ? [0, 1, 2, 4, 5, 6, 7, 9] : (bien.tipo_bien === "Impresoras") ?
                                [0, 1, 2, 4, 5, 6, 7, 8, 9] : [0, 1, 2, 3, 4, 5, 6, 7, 9]
                    }
                }
            ]
        },
        ajax: {
            url: 'ControllerTablas?Modulo=DetalleBienes&cve_bienes=' + bien.cve_bienes,
            dataSrc: ''
        },
        //Datos de la Tabla
        columns: [
            {data: 'cve_bienes'},
            {data: 'cve_mantenimiento'},
            {data: 'tipoMantenimiento'},
            {data: 'fallas'},
            {data: 'fecha_manteni'},
            {data: 'proximo_mantenimiento'},
            {data: 'dias_restantes'},
            {data: 'cambioET'},
            {data: 'tonnersCambiados'},
            {data: 'mant_descripcion'},
            {data: 'nombre_archivo'},
            {data: 'nombre_archivo_reporte'},
            {defaultContent: "<button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>smart_display</span></button>\n\
                              <button class='btn btn-sm btn-primary btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>"}

        ]
    });

//boton evidencia Video
    $("#DetallesBienes tbody").on('click', '.btnEvidencia', function () {
        let data = table.row($(this).parents()).data();
        var archivo = data.nombre_archivo; //capturo el ID 

        if (archivo === null || archivo === " ") {
            archivo = "nulo";
        }

        window.open('pages/evidencia.jsp?video=' + archivo, '_blank');

    });

    //boton evidencia Reporte
    $("#DetallesBienes tbody").on('click', '.btnEvidencia2', function () {
        let data = table.row($(this).parents()).data();
        var archivo = data.nombre_archivo_reporte; //capturo el ID 

        if (archivo === null || archivo === " ") {
            archivo = "nulo";
        }

        window.open('pages/evidencia.jsp?pdf=' + archivo, '_blank');

    });



//OCULTAR FILAS
    const fila_falla = table.column(3);
    const fila_tonnersCambiados = table.column(8);

    const num_bien = table.column(0);
    num_bien.visible(!num_bien.visible());

    if (bien.tipo_bien === "Extintores") {
        fila_falla.visible(!fila_falla.visible()); // true or false
        fila_tonnersCambiados.visible(!fila_tonnersCambiados.visible()); // true or false
    }

    if (bien.tipo_bien === "Impresoras") {
        fila_falla.visible(!fila_falla.visible()); // true or false
    }

    if (bien.tipo_bien === "Climas") {
        fila_tonnersCambiados.visible(!fila_tonnersCambiados.visible()); // true or false
    }

});