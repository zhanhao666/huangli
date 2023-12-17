import request from '@/utils/request'

export function getList(data) {
    return request({
        url: '/vue-element-admin/emp/getEmpList',
        method: 'post',
        data
    })
}

export function insertEmp(data) {
    return request({
        url: '/vue-element-admin/emp/insertEmp',
        method: 'post',
        data
    })
}

export function deleteEmp(id) {
    return request({
        url: '/vue-element-admin/emp/deleteEmp/'+id,
        method: 'delete'
    })
}

export function updateEmp(data) {
    return request({
        url: '/vue-element-admin/emp/updateEmp',
        method: 'post',
        data
    })
}
