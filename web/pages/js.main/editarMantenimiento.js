
//Asignar nombre de archivo al input
document.getElementById("evidencia").onchange = function () {
    var name = document.getElementById('evidencia').files[0].name;
    document.getElementById("archivo").innerHTML = name;
};

//Asignar nombre de archivo al input
document.getElementById("evidenciaReporte").onchange = function () {
    var name = document.getElementById('evidenciaReporte').files[0].name;
    document.getElementById("archivoReporte").innerHTML = name;
};


//---------------Genera las opciones de Inventarios--------------
function NumeroIventarios(tipo) {
    var $select = $('#numInventario');
    //Se se paran los Numeros de Inventarios
    $.each(data, function (i, val) {
        if (val.tipo_bien === tipo) {
            $select.append($('<option />', {value: (i + 1), text: val.num_inventario})
                    );
        }
    });
}

$(document).ready(function () {

//Obtiene los Numeros inventario
    var inventario = [];
    $.each(data, function (i, val) {
        inventario[i] = val.num_inventario;
    });
//obtiene los Numeros de ID
    var cve_bienes = [];
    $.each(data, function (i, val) {
        cve_bienes[i] = val.cve_bienes;
        //   $select2.append($('<option />', {value: (i + 1), text: val.cve_bienes})
        //);
    });
    var tipo_bien = [];
    $.each(data, function (i, val) {
        tipo_bien[i] = val.tipo_bien;
        //   $select3.append($('<option />', {value: (i + 1), text: val.tipo_bien})
        //);
    });
//---------------------------------

    function changeFunc(val) {
        var desactivar = 'none';
        if (val === "1") {
            var clic = false;
            llenar(clic, "Climas");
        }
        if (val === "2") {
            var clic = false;
            llenar(clic, "Extintores");
        }
        if (val === "3") {
            var clic = false;
            llenar(clic, "Impresoras");
        }
    }

//----------Selecciono climas------------
    function climas(accion) {
        var camposRe = ['Cambio', 'tonnerCambiar', 'CambiarET'];
        //Ocultar campos
        for (var i = 0; i < camposRe.length; i++) {
            document.getElementById(camposRe[i]).style.display = accion;
            //$(camposRe[i]).prop('required', true);
        }
        document.getElementById('tonnerCambiados').required = "";
        document.getElementById('seCambio').required = "";
        var vaciar = ['tonnerCambiados', 'seCambio', 'cve_bienes', 'fechaMantenimiento', 'mantenimientObservacion', 'num_mantenimiento'];
        for (var item in vaciar) {
            document.getElementById(vaciar[item]).value = "";
        }

    }


//-----------Selecciono extintor--------------
    function extintor(accion) {
        var camposRe = ['fallaComun', 'tonnerCambiar'];
        for (var i = 0; i < camposRe.length; i++) {
            document.getElementById(camposRe[i]).style.display = accion;
            //$(camposRe[i]).prop('required', true);
        }

        document.getElementById("tonnerCambiados").required = "";
        document.getElementById("fallas").required = "";
        document.getElementById("CambiarET").innerHTML = "Se cambio el Extintor";
        var vaciar = ['num_mantenimiento', 'tonnerCambiados', 'cve_bienes', 'fechaMantenimiento', 'mantenimientObservacion'];
        for (var item in vaciar) {
            document.getElementById(vaciar[item]).value = "";
        }
        document.getElementById('numInventario').value = "";
        document.getElementById('seCambio').value = "";
        document.getElementById('tipoMantenimiento').value = "";
    }


//---------selecciono impresora-----------------
    function impresora(accion) {

        document.getElementById('fallaComun').style.display = accion;
        document.getElementById('fallas').required = "";
        var vaciar = ['cve_bienes', 'fechaMantenimiento', 'seCambio', 'tipoMantenimiento', 'mantenimientObservacion', 'numInventario', 'num_mantenimiento'];
        for (var item in vaciar) {
            document.getElementById(vaciar[item]).value = "";
        }

        document.getElementById("CambiarET").innerHTML = "Se cambio el Tonner";
    }

//---------Activar campos posiblemente desactivados-------------
    function camposDesactivadosPosiblemente() {
        var camposVisibles = ['fechaMante', 'Cambio', 'tipoManteni', 'fallaComun', 'tonnerCambiar', 'manteObservacion',
            'evidenciaVista', 'evidenciaVista2', 'tipoManteni', 'CambiarET', 'NumInventario'];
        var camposRequiered = ["#fechaMantenimiento", "#seCambio", "#tipoMantenimiento", "#fallas"];
        for (var i = 0; i < camposVisibles.length; i++) {

            document.getElementById(camposVisibles[i]).style.display = 'block';
        }
        for (var i = 0; i < camposRequiered.length; i++) {
// document.getElementById(camposRequiered[i]).required = "required";
            $(camposRequiered[i]).prop('required', true);
        }

    }






//Llena los campos del Mantenimiento a editar
    llenar(true, datos.tipo_bien);

//------llena los campos con el mantenimiento-----
    function llenar(clic, tipo) {
        var desactivar = 'none';
        if (tipo === "Climas") {
            camposDesactivadosPosiblemente();
            climas(desactivar);
            $("#numInventario").find('option').not(':first').remove();
            NumeroIventarios('Climas');
            if (clic === true) {
                document.getElementById("tipoBien").value = "1";
            }
            camposllenar(1);
            clicInventario();
        }

        if (tipo === "Extintores") {
            camposDesactivadosPosiblemente();
            extintor(desactivar);
            $("#numInventario").find('option').not(':first').remove();
            NumeroIventarios('Extintores');
            if (clic === true) {
                document.getElementById("tipoBien").value = "2";
            }
            camposllenar(2);
            clicInventario();
        }

        if (tipo === "Impresoras") {
            camposDesactivadosPosiblemente();
            impresora(desactivar);
            $("#numInventario").find('option').not(':first').remove();
            NumeroIventarios('Impresoras');
            if (clic === true) {
                document.getElementById("tipoBien").value = "3";
            }
            camposllenar(3);
            $("#seCambio option:contains(" + datos.cambioET + ")").attr('selected', true);
            clicInventario();
            document.getElementById("tonnerCambiados").value = datos.tonnersCambiados;
        }
    }

    function camposllenar(tipo) {
        var cambioET = "";
        var tipoMantenimiento = "";
        var fallas = "";
        (datos.cambioET === "Si") ? cambioET = "1" : cambioET = "2";
        (datos.tipoMantenimiento === "Predictivo") ? tipoMantenimiento = "1" : (datos.tipoMantenimiento === "Preventivo")
                ? tipoMantenimiento = "2" : (datos.tipoMantenimiento === "Correctivo") ? tipoMantenimiento = "3" : tipoMantenimiento = "0";
        (datos.fallas === "No enfria") ? fallas = "1" : (datos.fallas === "Falta de Gas") ? fallas = "2" : (datos.fallas === "Fuga") ? fallas = "3" : (datos.fallas === "No enciende") ? fallas = "4" : fallas = "0";
        document.getElementById("cve_bienes").value = datos.cve_bienes;
        $("#cve_bienes").attr("readonly", true);
        document.getElementById("fechaMantenimiento").value = datos.fecha_manteni;
        document.getElementById("num_mantenimiento").value = datos.cve_mantenimiento;
        $("#num_mantenimiento").attr("readonly", true);
        $("#numInventario option:contains(" + datos.num_inventario + ")").attr('selected', true);
        $("#tipoMantenimiento option:contains(" + datos.tipoMantenimiento + ")").attr('selected', true);
        document.getElementById("mantenimientObservacion").value = datos.mant_descripcion;
        if (tipo === 1) {
            document.getElementById("fallas").value = fallas;
            document.getElementById("tipoMantenimiento").value = tipoMantenimiento;
            $("#numInventario option:contains(" + datos.num_inventario + ")").attr('selected', true);
        }

        if (tipo === 2) {
//$("#seCambio option:contains(" + datos.cambioET + ")").attr('selected', true);
            document.getElementById("seCambio").value = cambioET;
            document.getElementById("tipoMantenimiento").value = tipoMantenimiento;
        }
        if (tipo === 3) {
            document.getElementById("seCambio").value = cambioET;
            document.getElementById("tipoMantenimiento").value = tipoMantenimiento;
        }

    }


//Seleccionar un Numero de Inventario se asigna el ID de Ese Bien
    function  clicInventario() {
        var combo = document.getElementById("numInventario");
        var numInventario = combo.options[combo.selectedIndex].text;
        if (numInventario !== "Seleccionar..." || numInventario !== null) {
            for (var i = 0; i < inventario.length; i++) {
                if (inventario[i] === numInventario) {
//$("#cve_bienes").attr("readonly", true);
//Se asigna el Id que pertenece el Numero de Inventario
                    document.getElementById("cve_bienes").value = cve_bienes[i];
                    //Se Muestra el campo Numero de Inventario
                    document.getElementById("cve").style.display = 'block';
                }
            }
        }
    }



    $("#evidenciaEliminar").attr("readonly", true);
    document.getElementById("evidenciaEliminar").style.display = 'none';
    document.getElementById("evidenciaEliminar").value = datos.evidenciaVideo;

    $("#evidenciaReporteEliminar").attr("readonly", true);
    document.getElementById("evidenciaReporteEliminar").style.display = 'none';
    document.getElementById("evidenciaReporteEliminar").value = datos.evidenciaReporte;

    var ar = datos.nombre_archivo;
    if (ar.trim().length !== 0) {
        document.getElementById("archivo").innerHTML = datos.nombre_archivo;
    }
    var reporte = datos.nombre_archivo_reporte;
    if (reporte.trim().length !== 0) {
        document.getElementById("archivoReporte").innerHTML = datos.nombre_archivo_reporte;
    }



    $("#actualizar").click(function () {
        setTimeout(() => {
            //Cuando el boton sea desactivado se esta enviando el formulario y se activa el gif de Cargando
            if ($("#actualizar").is(":disabled")) {

                var content = "<div class='cargando_fondo'>" +
                        "<div class='loadgif'><div class='lds-roller'><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div></div></div>";
                $('#carga').append(content);
            }
        }, 1);
    });


});


