function onSearch() {

    var content = $('#search_content').val();

    window.location.href='/houselist/search=' + content;

}