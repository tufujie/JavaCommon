function printUser(type) {
    var userIDs = $.trim($('#userID').val());
    if (userIDs == null || userIDs === '') {
        userIDs = 1;
    }
    var url = "/print/printUser?ids=" + userIDs;
    if (type == 1) {
        location.href = url;
    } else if (type == 2) {
       window.open(url);
    }
}