function setHidden(id, checked) {
    $.ajax({
        type: 'get',
        url: '/admin/label/updateHidden?id=' + id + '&isHidden=' + checked,
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
        url: '/admin/label/updateData',
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

function addLabel(name) {
    $.ajax({
        type: 'get',
        url: '/admin/label/addLabel?name=' + name,
        dataType: 'json',
        success: function (data) {
            if (data.code === 0) {
                layer.msg("添加成功")
            } else {
                layer.msg("添加失败")
            }
        }
    })
}

function deleteLabel(id) {
    $.ajax({
        type: 'get',
        url: '/admin/label/deleteLabel?id=' + id,
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