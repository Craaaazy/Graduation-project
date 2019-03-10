$(document).ready(function () {
    $.ajax({
        url:'/user/info',
        type:'get',

        success:function(res){

            $('#house_sold').text(res.house_sold);
            $('#total_earn').text('￥ ' + res.total_earn);

            $('div [id="head"]').each(function () {
                $(this).attr("src",'http://localhost:8050/image/' + res.head_icon);
            })

            $('div [id="name"]').each(function(){
                $(this).text(res.username);
            })

            $('div [id="role"]').each(function(){
                $(this).text("user");
            })



        },
        error:function (res) {
            alert('error');
        }
    })
})
