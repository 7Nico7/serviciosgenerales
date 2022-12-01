

var ID = data.cve_recibo;
var archivo = "";
const datosTabla = [];
var nombre = ["No.Recibo", "No.Medidor", "Año", "Periodo", "Saldo", "Consumo", "Nombre de Archivo", "Archivo"];
(data.nombre_archivo === " ") ? archivo = "nulo" : archivo = data.nombre_archivo;

for (let dato in data) {
    if (data.year === data[dato]) {
        datosTabla.push(data.cve_medidor);
    } else if (data.periodo === data[dato]) {
        datosTabla.push(data.year);
    } else if (data.consumo === data[dato]) {
        datosTabla.push(data.periodo);
    } else if (data.archivo === data[dato]) {
        datosTabla.push(data.nombre_archivo);
    } else if (data.nombre_archivo === data[dato]) {
        datosTabla.push(data.consumo);
    } else if (data.cve_medidor === data[dato]) {

        datosTabla.push("<a id ='pdf' href='pages/evidencia.jsp?pdf=" + archivo + "' target='_blank'>archivo</a>");
    } else {
        datosTabla.push(data[dato]);
    }
}

var reporte = data.nombre_archivoReporte;
if (reporte.trim().length === 0 || pdf === null || pdf === undefined) {
    reporte = "nulo";
}


var content = " <table id='infoRecibo' class='table table-bordered table-responsive'>";
var cont = 0;
var numero = esPar(datosTabla.length);
function esPar(numero) {
    return ((numero % 2) === 0) ? true : false;
}

var control = (datosTabla.length / 2) * 2;

for (var i = 0; i < (datosTabla.length / 2); i++) {

    content += "<tr><th>" + nombre[cont] + "</th>" +
            "<td>" + datosTabla[cont] + "</td>";
    cont++;
    if (numero === true) {
        content += "<th>" + nombre[cont] + "</th>" +
                "<td>" + datosTabla[cont] + "</td>" + "</tr>";
        cont++;
    } else if (numero === false) {
        if (control === cont) {
            content += "<th>" + " " + "</th>" +
                    "<td>" + " " + "</td>" + "</tr>";
        } else {
            content += "<th>" + nombre[cont] + "</th>" +
                    "<td>" + datosTabla[cont] + "</td>" + "</tr>";
            cont++;
        }
    }

}
content += "</table>";
//document.getElementById('generaTabla').innerHTML = content;


$(function () {
    $("#Excel").click(function (e) {
        var table = $("#infoRecibo");
        if (table && table.length) {
            $(table).table2excel({
                exclude: ".noExl",
                name: "Recibo",
                filename: "Detalle Recibo" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
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
    doc.text(20, 30, "Recibo reporte");

    // doc.addImage(imgData, 'JPEG', 10, 40, 180, 180);
    // imgData.onload = function () {
    doc.save("mipdf.pdf");
    //};
});



$(document).ready(function () {
//SE genera la Tabla 
    var table = $('#DataTableRecibo').DataTable({
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
            url: 'ControllerTablas?Modulo=ReciboID&cve_recibo=' + data.cve_recibo,
            dataSrc: ''
        },
        //Valida si la lista es extintores para eliminar
        columns:
                [
                    {data: 'cve_recibo'},
                    {data: 'cve_medidor'},
                    {data: 'medidor'},
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
                    {defaultContent: "<a class='btn btn-sm btn-success' id ='pdf' href='pages/evidencia.jsp?pdf=" + archivo + "' target='_blank'><span class='material-icons'>picture_as_pdf</span></a> \n\
                    <a class='btn btn-primary btn-sm' id ='pdf' href='pages/evidencia.jsp?pdf=" + reporte + "' target='_blank'><span class='material-icons'>picture_as_pdf</span></a>"}
                ]
    });
    const fila_archivo = table.column(10);
    fila_archivo.visible(!fila_archivo.visible());

    const archivo_reporte = table.column(12);
    archivo_reporte.visible(!archivo_reporte.visible());
});


