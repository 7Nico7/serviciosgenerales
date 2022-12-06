var $select = $('#vehiculo');
$.each(data, function (i, val) {
    $select.append($('<option />', {value: val.cve_vehiculo, text: val.vehiculo + " " + val.marca + " " + val.modelo})
            );
});

//Nombre archivo al Input
document.getElementById("evidencia").onchange = function () {
    var name = document.getElementById('evidencia').files[0].name;
    document.getElementById("archivo").innerHTML = name;
};

document.getElementById("evidencia2").onchange = function () {
    var name = document.getElementById('evidencia2').files[0].name;
    document.getElementById("archivo2").innerHTML = name;
};