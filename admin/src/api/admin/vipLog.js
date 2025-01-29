import request from '@/utils/request'

// 查询VIP操作日志列表
export function listVipLog(query) {
  return request({
    url: '/admin/vipLog/list',
    method: 'get',
    params: query
  })
}

// 查询VIP操作日志详细
export function getVipLog(vipLogId) {
  return request({
    url: '/admin/vipLog/' + vipLogId,
    method: 'get'
  })
}

// 新增VIP操作日志
export function addVipLog(data) {
  return request({
    url: '/admin/vipLog',
    method: 'post',
    data: data
  })
}

// 修改VIP操作日志
export function updateVipLog(data) {
  return request({
    url: '/admin/vipLog',
    method: 'put',
    data: data
  })
}

// 删除VIP操作日志
export function delVipLog(vipLogId) {
  return request({
    url: '/admin/vipLog/' + vipLogId,
    method: 'delete'
  })
}
