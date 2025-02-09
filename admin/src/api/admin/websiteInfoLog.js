import request from '@/utils/request'

// 查询网站信息日志列表
export function listWebsiteInfoLog(query) {
    return request({
        url: '/admin/websiteInfoLog/list',
        method: 'get',
        params: query
    })
}

// 查询网站信息日志详细
export function getWebsiteInfoLog(websiteInfoLogId) {
    return request({
        url: '/admin/websiteInfoLog/' + websiteInfoLogId,
        method: 'get'
    })
}

// 新增网站信息日志
export function addWebsiteInfoLog(data) {
    return request({
        url: '/admin/websiteInfoLog',
        method: 'post',
        data: data
    })
}

// 修改网站信息日志
export function updateWebsiteInfoLog(data) {
    return request({
        url: '/admin/websiteInfoLog',
        method: 'put',
        data: data
    })
}

// 删除网站信息日志
export function delWebsiteInfoLog(websiteInfoLogId) {
    return request({
        url: '/admin/websiteInfoLog/' + websiteInfoLogId,
        method: 'delete'
    })
}

// 获取网站信息日志映射数据（若有需要）
export function indexWebsiteInfoLog() {
    return request({
        url: '/admin/websiteInfoLog/index',
        method: 'get'
    })
}
