var $select = $('#medidor');
$.each(data, function (i, val) {
      $select.append($('<option />', {value: val.cve_medidor, text: val.medidor})
    );
});

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