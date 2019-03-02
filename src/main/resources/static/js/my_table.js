$(document).ready(function () {

    $('#myTable').DataTable({
        "oLanguage":{
            "sZeroRecords":"查不到数据...",
            "sInfo":"当前显示 _START_ 到 _END_ 条， 共_TOTAL_条",
            "oPaginate":{
                "sFirst":"首页",
                "sPrevious":"上一页",
                "sNext":"下一页",
                "sLast":"末页"
            }
        },
        "autoWidth": false

    });

});


function onDelete(btn) {
    var id = $(btn).parent("td").parent("tr").find("td:eq(0)").text();

    $.confirm({
        title: '确认删除？',
        content: '该操作不可撤销!',
        buttons: {
            confirm: function () {
                $.ajax({
                    url:'/owner/myHouse/' + id,
                    type:'delete',

                    success:function (res){
                        $.alert('删除成功!');
                        window.location.reload();
                    },
                    error:function(res){

                    }

                })

            },
            cancel: function () {}

        }
    });

}
