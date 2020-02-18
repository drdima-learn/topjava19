// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
    makeFilter();
    makeResetFilter();
});

function makeFilter(){
    $("#buttonFilter").click(function(){
        $.ajax({
            type: "GET",
            url: context.ajaxUrl + "getBetween",
            data: $('#filterForm').serialize()
        }).done(function () {
            //$("#editRow").modal("hide");
            updateTable();
            successNoty("Filtered");
        });
    });
}

function makeResetFilter(){
    $("#resetFilter").click(function(){
        $("#filterForm").each(function(){
            $(this).find(":input").val("");
        });
        $("#buttonFilter").click();
    });
}

