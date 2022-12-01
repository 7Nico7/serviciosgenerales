
        var fecha = {};
        var asunto = {};
        var cve_vehiculo = {};
        var cve_salidas = {};
        var destino = {};
        var hora_entrada = {};
        var hora_salida = {};
        var gasolina_inicial = {};
        var gasolina_final = {};
        var kilometraje_inicial = {};
        var kilometraje_final = {};
        var tiempo_ruta = {};
        var usuarios = {};
        var estado = {};
        var conductor = {};
        var observaciones = {};

        var $select = $('#cve_salidas');

        $.each(data, function (i, val) {
            fecha[i] = val.fecha;
            asunto[i] = val.asunto;
            cve_vehiculo[i] = val.cve_vehiculo;
            cve_salidas [i] = val.cve_salidas;
            destino[i] = val.destino;
            hora_entrada [i] = val.hora_entrada;
            hora_salida [i] = val.hora_salida;
            gasolina_inicial [i] = val.gasolina_inicial;
            gasolina_final [i] = val.gasolina_final;
            kilometraje_inicial [i] = val.kilometraje_inicial;
            kilometraje_final [i] = val.kilometraje_final;
            tiempo_ruta [i] = val.tiempo_ruta;
            usuarios [i] = val.usuarios;
            estado[i] = val.status;
            conductor [i] = val.conductor;
            observaciones [i] = val.observaciones;
            $select.append($('<option />', {value: val.cve_salidas, text: val.cve_salidas + ", " + val.fecha + ", " + val.asunto})
                    );
        });


        var $select2 = $('#vehiculo');
        $.each(dataV, function (i, val) {
            $select2.append($('<option />', {value: val.cve_vehiculo, text: val.vehiculo + " " + val.marca + " " + val.modelo})
                    );
        });



        $("#cve_salidas").change(function () {
            var opcion = $("#cve_salidas").val();

            if (opcion !== null && opcion !== " " && opcion !== "" && opcion > 0) {
                var campos = ["asunto", "fecha", "vehiculo", "hora_entrada", "hora_salida", "k_inicial", "k_final", "g_inicial", "g_final", "conductor",
                    "usuarios", "destino", "observaciones"];
                var datos = [asunto[opcion - 1], fecha[opcion - 1], cve_vehiculo[opcion - 1], hora_entrada[opcion - 1], hora_salida[opcion - 1],
                    kilometraje_inicial[opcion - 1], kilometraje_final[opcion - 1], gasolina_inicial[opcion - 1], gasolina_final[opcion - 1], conductor[opcion - 1], usuarios[opcion - 1],
                    destino[opcion - 1], observaciones[opcion - 1]];

                for (var item in campos) {
                    document.getElementById(campos[item]).value = datos[item];
                }
                var statu = estado[opcion - 1];
                $("#status").find('option:contains("' + statu + '")').prop('selected', true);
            }

        });
