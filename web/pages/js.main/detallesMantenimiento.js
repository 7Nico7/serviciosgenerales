
console.log(bien);
var datosTabla;


if (bien.tipo_bien === "Climas") {
    datosTabla = [bien.cve_bienes, bien.cve_mantenimiento, bien.tipo_bien, bien.num_inventario, bien.fallas, bien.tipoMantenimiento, bien.mant_descripcion, bien.fecha_manteni,
        bien.proximo_mantenimiento, bien.dias_restantes];
    nombre = ["Numero de Bien", "Numero de Mantenimiento", "Tipo de Bien", "Inventario", "Fallas", "Tipo de Mantenimiento", "Descripción de Mantenimiento", "Fecha de Mantenimiento",
        "Proximo Mantenimiento", "Dias restantes"];
}

if (bien.tipo_bien === "Extintores") {
    datosTabla = [bien.cve_bienes, bien.cve_mantenimiento, bien.tipo_bien, bien.num_inventario, bien.serie, bien.tipoMantenimiento, bien.mant_descripcion, bien.fecha_manteni,
        bien.proximo_mantenimiento, bien.dias_restantes];
    nombre = ["Numero de Bien", "Numero de Mantenimiento", "Tipo de Bien", "Inventario", "Serie", "Tipo de Mantenimiento", "Descripción de Mantenimiento", "Fecha de Mantenimiento",
        "Proximo Mantenimiento", "Dias restantes"];
}

if (bien.tipo_bien === "Impresoras") {
    datosTabla = [bien.cve_bienes, bien.cve_mantenimiento, bien.tipo_bien, bien.num_inventario, bien.cambioET, bien.tonnersCambiados,
        bien.tipoMantenimiento, bien.mant_descripcion, bien.fecha_manteni,
        bien.proximo_mantenimiento, bien.dias_restantes];
    nombre = ["Numero de Bien", "Numero de Mantenimiento", "Tipo de Bien", "Inventario", "Se cambio Tornners", "Torners Cambiados",
        "Tipo de Mantenimiento", "Descripción de Mantenimiento", "Fecha de Mantenimiento",
        "Proximo Mantenimiento", "Dias restantes"];
}


var content = " <table id='infoBienes' class='table table-bordered table-responsive'>";
var cont = 0;
var numero = esPar(datosTabla.length);
function esPar(numero)
{
    return ((numero % 2) === 0) ? true : false;
}

var control = (datosTabla.length / 2) * 2;

for (var i = 0; i < (datosTabla.length / 2); i++) {

    content += "<tr><th scope='row'>" + nombre[cont] + "</th>" +
            "<td>" + datosTabla[cont] + "</td>";
    cont++;
    if (numero === true) {
        content += "<th scope='row'>" + nombre[cont] + "</th>" +
                "<td>" + datosTabla[cont] + "</td>" + "</tr>";
        cont++;
    } else if (numero === false) {
        if (control === cont) {
            content += "<th scope='row'>" + " " + "</th>" +
                    "<td>" + " " + "</td>" + "</tr>";
        } else {
            content += "<th scope='row'>" + nombre[cont] + "</th>" +
                    "<td>" + datosTabla[cont] + "</td>" + "</tr>";
            cont++;
        }
    }

}
content += "</table>";
document.getElementById('generaTabla').innerHTML = content;


//--------------------------

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