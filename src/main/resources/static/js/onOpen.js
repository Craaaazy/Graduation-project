
//我已经完全迷了，这个文件删了也没关系 但是我头像什么的都是靠这个ajax来获取的，删了也可以照常运行，alert也不会调用。
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
                if(res.role == 'ROLE_ADMIN')
                    $(this).text("administator");
                else
                    $(this).text("user");
            })

            if(res.role == 'ROLE_ADMIN'){
                $('#admin_href').removeAttr("hidden");
                $('#bill_href').removeAttr("hidden");
            }

            $('#project_num').text(res.project_num);

            $('#billing').text('￥' + res.balance);

        },
        error:function (res) {
            alert('error');
        }
    })
})
