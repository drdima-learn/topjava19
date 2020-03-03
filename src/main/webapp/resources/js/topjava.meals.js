var mealAjaxUrl = "ajax/profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        dataType: "jsong",
        contentType: "application/jsong",
        // contents: {
        //     mycustomtype: /mycustomtype/
        // },
        converters: {
            "json jsong": function( result ) {
                // Какие то действия
                console.log("pre-processing..." + result);
                return "dddTttt";
            }
        },
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl ,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            createdRow: function (row, data, dataIndex) {
                $(row).attr('data-mealExcess', data.excess);
            },
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return date.substring(0, 10) + " " + date.substring(11, 16);
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {

                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        }),
        updateTable: updateFilteredTable
        // updateTable: function () {
        //     $.get(mealAjaxUrl, updateTableByData);
        // }
    });
});