function updateAdminArticleComment(id, content) {
    $.ajax({
        type: 'get',
        url: '/admin/comment/updateAdminArticleComment?id=' + id + '&content=' + content,
        dataType: 'json',
        success: function (data) {
            if (data.code === 0) {
                layer.msg("修改成功")
            } else {
                layer.msg("修改失败")
            }
        }
    })
}

function updateAdminLeaveComment(id, content) {
    $.ajax({
        type: 'get',
        url: '/admin/comment/updateAdminLeaveComment?id=' + id + '&content=' + content,
        dataType: 'json',
        success: function (data) {
            if (data.code === 0) {
                layer.msg("修改成功")
            } else {
                layer.msg("修改失败")
            }
        }
    })
}

function deleteAdminArticleComment(id) {
    $.ajax({
        type: 'get',
        url: '/admin/comment/deleteAdminArticleComment?id=' + id,
        dataType: 'json',
        success: function (data) {
            if (data.code === 0) {
                layer.msg("删除成功")
            } else {
                layer.msg("删除失败")
            }
        }
    })
}

function deleteAdminLeaveComment(id) {
    $.ajax({
        type: 'get',
        url: '/admin/comment/deleteAdminLeaveComment?id=' + id,
        dataType: 'json',
        success: function (data) {
            if (data.code === 0) {
                layer.msg("删除成功")
            } else {
                layer.msg("删除失败")
            }
        }
    })
}