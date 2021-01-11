function addOrUpdateArticle(id, title, des, cover, content, status, show, top, login, labels) {
    $.ajax({
        type: 'post',
        url: '/admin/article/addOrUpdateArticle',
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
            "id": id,
            "title": title,
            "des": des,
            "cover": cover,
            "content": content,
            "status": status,
            "show": show,
            "top": top,
            "login": login,
            "labels": labels
        }),
        success: function (data) {
            if (data.code === 0) {
                layer.msg("添加成功")
                return data.id;
            } else {
                layer.msg("添加失败")
            }
        }
    })
}


function deleteCoverImage(ossPath, force) {
    let code;
    $.ajax({
        type: 'get',
        url: '/admin/image/deleteCoverImage?ossPath=' + ossPath + '&force=' + force,
        dataType: 'json',
        async: false,
        success: function (data) {
            code = data.code;
            if (data.code === 0) {
                layer.msg("删除成功")
            } else {
                layer.msg("删除失败")
            }
        }
    })
    return code;
}

function deleteArticleImage(ossPath, force) {
    let code;
    $.ajax({
        type: 'get',
        url: '/admin/image/deleteArticleImage?ossPath=' + ossPath + '&force=' + force,
        dataType: 'json',
        async: false,
        success: function (data) {
            code = data.code;
            if (data.code === 0) {
                layer.msg("删除成功")
            } else {
                layer.msg("删除失败")
            }
        }
    })
    return code;
}


function setHidden(id, checked) {
    $.ajax({
        type: 'get',
        url: '/admin/article/updateHidden?id=' + id + '&isHidden=' + checked,
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

function setTop(id, topWeight) {
    $.ajax({
        type: 'get',
        url: '/admin/article/updateTop?id=' + id + '&topWeight=' + topWeight,
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

function setData(id, filed, value) {
    $.ajax({
        type: 'post',
        url: '/admin/article/updateData',
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
            "id": id,
            [filed]: value,
        }),
        success: function (data) {
            if (data.code === 0) {
                layer.msg("修改成功")
            } else {
                layer.msg("修改失败")
            }
        }
    })
}

function recycleArticle(id) {
    $.ajax({
        type: 'get',
        url: '/admin/article/recycleArticle?id=' + id,
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

function restoreArticle(id) {
    $.ajax({
        type: 'get',
        url: '/admin/article/restoreArticle?id=' + id,
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

function deleteArticle(id) {
    $.ajax({
        type: 'get',
        url: '/admin/article/deleteArticle?id=' + id,
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