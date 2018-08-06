/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    obtener_checkbox();
});

$("#insertar").click(function () {
    $("#insertar").html("<i class='fa fa-clock-o'></i> GUARDANDO..");
    hobbies = [];
    $('input[type="checkbox"]:checked').each(function () {
        hobbies.push($(this).attr("name"));
    });
    console.log("hobbies " + hobbies);
    var data = {
        "hobbies": hobbies.toString()
                // "hobbies": hobbies
    };
    $.ajax({
        type: 'POST',
        url: 'checkbox?accion=agregar',
        data: data,
        success: function (data) {
            console.log("Respuesta de Registro" + data);
            $("#insertar").html("<i class='fa fa-plus'></i> GUARDAR");
        },
        error: function (errorThrown, textStatus, jqXHR) {
            alertify.error("Un error ha ocurrido");
            console.log("Un error ha ocurrido" + errorThrown);
        }
    });


});


function obtener_checkbox() {
    $.ajax({
        url: "checkbox?accion=lista",
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            $.each(data, function (i, valorItem) {
                var cabecera = '<div class="form-check">';
                var pie = '</div>';
                $('#data').append(cabecera + '<label><input type="checkbox" id="cbo' + [valorItem.descripcion] + '" class="checkbox-mo" name="' + [valorItem.descripcion] + '"  />' + [valorItem.descripcion] + '</label>' + pie);
                console.log("" + [valorItem.valor] + "");
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Un error ha ocurrido: " + errorThrown);
        }

    });
}


function obtener(id) {

    $('#data input[type="checkbox"]').prop("checked", false);
    $.ajax({
        type: 'GET',
        url: "checkbox?accion=obtener&id=" + id,
        dataType: 'json',
        success: function (data) {
            console.log(data);

            for (var dt in data)
            {
                console.log(dt)
                console.log(data[dt])
                if (data[dt] === 1) {
                    $("#cbo" + dt).prop("checked", true)
                }
            }
//            $.each(data, function () {
//                var seleccion = data.data1;
//                $("input[name='hobbies'][value='" + seleccion + "']").attr('checked', 'checked');
//            });
        },
        error: function (jqXHR, textStatus, errorhrown) {

            console.log(jqXHR)
            console.log(textStatus)
            console.log(errorhrown)

            console.log("Un error ha ocurrido")
        }


    });
}