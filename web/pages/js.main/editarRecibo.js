var $select = $('#medidor');
$.each(data, function (i, val) {
    $select.append($('<option />', {value: val.cve_medidor, text: val.medidor})
            );
});


console.log(recibo);

document.getElementById("cve_recibo").value = recibo.cve_recibo;
document.getElementById("medidor").value = recibo.cve_medidor;
document.getElementById("periodoInicio").value = recibo.periodoInicio;
document.getElementById("periodoFinal").value = recibo.periodoFinal;
document.getElementById("consumo").value = recibo.consumo;
document.getElementById("saldo").value = recibo.saldo;
document.getElementById("eliminarArchivoCFE").value = recibo.archivo;

var re = recibo.nombre_archivo;

if (re.trim().length !== 0) {
    document.getElementById("archivoCFENombre").innerHTML = recibo.nombre_archivo;
}

var rep =  recibo.nombre_archivoReporte;
console.log(rep.trim().length);
if (rep.trim().length !== 0) {
    document.getElementById("archivoReporteNombre").innerHTML = recibo.nombre_archivoReporte;

}


//Nombre archivo al Input
document.getElementById("archivoCFE").onchange = function () {
    var name = document.getElementById('archivoCFE').files[0].name;
    document.getElementById("archivoCFENombre").innerHTML = name;
};

//Nombre archivo al Input
document.getElementById("archivoReporte").onchange = function () {
    var name = document.getElementById('archivoReporte').files[0].name;
    document.getElementById("archivoReporteNombre").innerHTML = name;
};