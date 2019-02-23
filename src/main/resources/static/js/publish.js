$(document).ready(function () {
    $('.sr_btn').on('click', function (event) {

        if($(this).get(0).innerHTML == "sell"){
            var obj = $('#sell');
        }else {
            var obj = $('#rent')
        }

        if(obj.attr("disabled")){
            obj.removeAttr("disabled");
        }else{
            obj.attr("disabled", "true");
            obj.val('');
        }

    })

    $('#publish_btn').on('click', function (event) {
        event.preventDefault();
        var data = new Object();

        data.title = $('#title').val();
        data.locate = $('#locate').val();
        data.rent = $('#rent').val();
        data.sell = $('#sell').val();
        data.zone = $('#zone').val();
        data.detail = $('#detail').val();

        $.ajax({
            url:'/owner/publish',
            type:'POST',
            contentType: 'application/json',
            // dataType:'json',
            data:JSON.stringify(data),

            success:function (res) {
                console.log("success");
            },
            error:function (res) {
                console.log("error");
            }

        });

        return false;

    });

})


