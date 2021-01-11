function setHidden(id, checked) {
    $.ajax({
        type: 'get',
        url: '/admin/link/updateHidden?id=' + id + '&isHidden=' + checked,
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
        url: '/admin/link/updateData',
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

function addLink(uid, title, icon, des, url) {
    $.ajax({
        type: 'post',
        url: '/link/addLink',
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
            "uid": uid,
            "title": title,
            "icon": icon,
            "des": des,
            "url": url
        }),
        success: function (data) {
            if (data.code === 0) {
                layer.msg("添加成功")
            } else {
                layer.msg("添加失败")
            }
        }
    })
}

function deleteLink(id) {
    $.ajax({
        type: 'get',
        url: '/admin/link/deleteLink?id=' + id,
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