$(function () {
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
})

// $('#publish_btn').on('click', function (event) {
//     event.preventDefault();
//     var data = new Object();
//
// })



