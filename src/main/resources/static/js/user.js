function login() {
    location.href = '/user/qqLogin?currentUrl=' + location.pathname
}

function logout() {
    var $ = layui.jquery;
    $.ajax({
        type: 'get',
        url: '/user/logout',
        dataType: 'json',
        success: function (data) {
            if (data === 200) {
                location.reload();
            } else {
                layer.msg("退出失败")
            }
        }
    })
}