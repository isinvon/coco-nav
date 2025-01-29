import request from '@/utils/request'

// 查询友链操作日志列表
export function listFriendlinkLog(query) {
  return request({
    url: '/admin/friendlinkLog/list',
    method: 'get',
    params: query
  })
}

// 查询友链操作日志详细
export function getFriendlinkLog(friendlinkLogId) {
  return request({
    url: '/admin/friendlinkLog/' + friendlinkLogId,
    method: 'get'
  })
}

// 新增友链操作日志
export function addFriendlinkLog(data) {
  return request({
    url: '/admin/friendlinkLog',
    method: 'post',
    data: data
  })
}

// 修改友链操作日志
export function updateFriendlinkLog(data) {
  return request({
    url: '/admin/friendlinkLog',
    method: 'put',
    data: data
  })
}

// 删除友链操作日志
export function delFriendlinkLog(friendlinkLogId) {
  return request({
    url: '/admin/friendlinkLog/' + friendlinkLogId,
    method: 'delete'
  })
}
