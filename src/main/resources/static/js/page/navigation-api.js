function addNavigation(title, url) {
    $.ajax({
        type: 'post',
        url: '/admin/navigation/addNavigation',
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify({
            "title": title,
            "url": url,
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

function deleteNavigation(id) {
    $.ajax({
        type: 'get',
        url: '/admin/navigation/deleteNavigation?id=' + id,
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

function setData(id, filed, value) {
    $.ajax({
        type: 'post',
        url: '/admin/navigation/updateData',
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

function setHidden(id, checked) {
    $.ajax({
        type: 'get',
        url: '/admin/navigation/updateHidden?id=' + id + '&isHidden=' + checked,
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


function setLeft(id, checked) {
    $.ajax({
        type: 'get',
        url: '/admin/navigation/updateLeft?id=' + id + '&isLeft=' + checked,
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

function setBottom(id, checked) {
    $.ajax({
        type: 'get',
        url: '/admin/navigation/updateBottom?id=' + id + '&isBottom=' + checked,
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

