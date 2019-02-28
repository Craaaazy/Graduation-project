$(document).ready(function () {
    $('#publish_btn').on('click', function (event) {
        event.preventDefault();
        var data = new Object();

        data.title = $('#house_title').val();
        data.locate = $('#locate').val();
        data.rent = $('#rentPrice').val();
        data.sell = $('#sellPrice').val();
        data.zone = $('#zone').val();
        data.detail = $('#detail').val();

        $.ajax({
            url:'/owner/publish',
            type:'POST',
            contentType: 'application/json',
            // dataType:'json',
            data:JSON.stringify(data),

            success:function (res) {
                $('#submit_result').text("上传成功");
                $('#submit_result').css('color', 'green');
                $('#publish_btn').attr("disabled","disabled");
                console.log(res);
            },
            error:function (res) {
                $('#submit_result').text("Upload failed, " + res);
                $('#submit_result').css('color', 'red');
                console.log(res);
            }

        });

        return false;

    });
})


