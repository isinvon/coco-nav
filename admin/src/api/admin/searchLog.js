import request from '@/utils/request'

// 查询搜索日志列表
export function listSearchLog(query) {
  return request({
    url: '/admin/searchLog/list',
    method: 'get',
    params: query
  })
}

// 查询搜索日志详细
export function getSearchLog(searchLogId) {
  return request({
    url: '/admin/searchLog/' + searchLogId,
    method: 'get'
  })
}

// 新增搜索日志
export function addSearchLog(data) {
  return request({
    url: '/admin/searchLog',
    method: 'post',
    data: data
  })
}

// 修改搜索日志
export function updateSearchLog(data) {
  return request({
    url: '/admin/searchLog',
    method: 'put',
    data: data
  })
}

// 删除搜索日志
export function delSearchLog(searchLogId) {
  return request({
    url: '/admin/searchLog/' + searchLogId,
    method: 'delete'
  })
}
