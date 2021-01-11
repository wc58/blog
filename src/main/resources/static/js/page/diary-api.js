function deleteDiaryImage(ossPath, force) {
    let code;
    $.ajax({
        type: 'get',
        url: '/admin/image/deleteDiaryImage?ossPath=' + ossPath + '&force=' + force,
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
        url: '/admin/diary/updateHidden?id=' + id + '&isHidden=' + checked,
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
        url: '/admin/diary/updateData',
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

function recycleDiary(id) {
    $.ajax({
        type: 'get',
        url: '/admin/diary/recycleDiary?id=' + id,
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

function restoreDiary(id) {
    $.ajax({
        type: 'get',
        url: '/admin/diary/restoreDiary?id=' + id,
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

function deleteDiary(id) {
    $.ajax({
        type: 'get',
        url: '/admin/diary/deleteDiary?id=' + id,
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