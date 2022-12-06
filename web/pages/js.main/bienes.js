$(document).ready(function () {

    // Elimina la columna Si la lista es Extintores
    if (tipo === 'extintores') {
        $("#thSerie").remove();
    }
//SE genera la Tabla 
    var table = $('#example').DataTable({
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
                        columns: (tipo === 'extintores') ? [0, 1, 2, 4, 5] : [0, 1, 2, 3, 4, 5]
                    }
                },
                {
                    //definimos estilos del boton de excel
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
                        columns: (tipo === 'extintores') ? [0, 1, 2, 4, 5] : [0, 1, 2, 3, 4, 5]
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
                        columns: (tipo === 'extintores') ? [0, 1, 2, 4, 5] : [0, 1, 2, 3, 4, 5]
                    }
                }
            ]
        },
        ajax: {
            url: 'ControllerTablas?tabla=' + tipo + '&Modulo=Bienes',
            dataSrc: ''
        },
        //Valida si la lista es extintores para eliminar
        columns: (tipo === 'extintores') ?
                [
                    {data: 'cve_bienes'},
                    {data: 'tipo_bien'},
                    {data: 'num_inventario'},

                    {data: 'responsable'},
                    {data: 'status'},
                    {defaultContent: " <button class='btn btn-sm btn-info btnEditar'><span class='material-icons'>edit</span></button>\n\
                    <button  class='btnBorrar btn btn-primary btn-sm'><span class='material-icons'>delete</span></button>\n\
                    <button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>smart_display</span></button>\n\
                    <button class='btn btn btn-primary btn-sm btnInfo'><span class='material-icons'>feed</span></button>"}
                ]
                :
                [
                    {data: 'cve_bienes'},
                    {data: 'tipo_bien'},
                    {data: 'num_inventario'},
                    {data: 'serie'},
                    {data: 'responsable'},
                    {data: 'status'},
                    {defaultContent: " <button class='btn btn-sm btn-info btnEditar'><span class='material-icons'>edit</span></button>\n\
                    <button  class='btnBorrar btn btn-primary btn-sm'><span class='material-icons'>delete</span></button>\n\
                    <button class='btn btn-sm btn-success btnEvidencia1'><span class='material-icons'>smart_display</span></button>\n\
                    <button class='btn btn-sm btn-primary btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>\n\
                    <button class='btn btn btn-primary btn-sm btnInfo'><span class='material-icons'>feed</span></button>"}
                ]
    });



    //btoton Evidencia 
    $("#example tbody").on('click', '.btnEvidencia1', function () {
        let data = table.row($(this).parents()).data();
        evidencia("video", data);
    });

    $("#example tbody").on('click', '.btnEvidencia2', function () {
        let data = table.row($(this).parents()).data();
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

    //boton editar
    $("#example tbody").on('click', '.btnEditar', function () {
        let data = table.row($(this).parents()).data();
        var cve_bienes = data.cve_bienes; //capturo el ID 
        var tipo_bien = data.tipo_bien;
        //Se envia a un Formulario
        document.getElementById("cve_bienes").value = cve_bienes;
        document.getElementById("tipo_bien").value = tipo_bien;
        //Se da clic para enviar el id y el tipo de bien
        $('#EnviarBien').click();
    });

    //boton Información Detallada del Bien
    $("#example tbody").on('click', '.btnInfo', function () {
        let data = table.row($(this).parents()).data();
        var cve_bienes = data.cve_bienes; //capturo el ID 
        var tipo_bien = data.tipo_bien;
        //Se envia a un Formulario
        document.getElementById("Cve_Bienes").value = cve_bienes;
        document.getElementById("Tipo_bien").value = tipo_bien;
        //Se da clic para enviar el id y el tipo de bien
        $('#info').click();
    });


    //btoton borrar 
    $("#example tbody").on('click', '.btnBorrar', function (e) {
        // e.preventDefault(); //evita la carga total de la apagina
        let data = table.row($(this).parents()).data();
        var cve_bienes = data.cve_bienes; //capturo el ID 
        var eliminar = confirm('Eliminar Registro ');
        if (eliminar) {
            var datos = 'cve_bienes=' + cve_bienes;
            $.ajax({
                url: "ControllerEliminar?Eliminar=eliminarBien", type: 'POST', data: datos,
                success: function (data) {

                    if (data) {
                        if (data.trim() === "nulo") {
                            swal('Error!', 'El Parametro Eliminar es Nulo', 'error');
                        } else if (data.trim() === "desconocido") {
                            swal('Error!', 'El Parametro Eliminar Es Desconocido', 'error');
                        }
                        if (data.trim() === "eliminado") {
                            swal('Mensaje del sistema', 'Se Elimino El Registro', 'success');
                        }
                    } else {
                        swal('Error!', 'Ocurrio un erro al eliminar', 'error');
                    }


                    table.ajax.reload(null, false);
                }
            });
        }
    });
});