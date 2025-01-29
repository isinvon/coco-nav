import request from '@/utils/request'

// 查询积分流水列表
export function listPointLog(query) {
  return request({
    url: '/admin/pointLog/list',
    method: 'get',
    params: query
  })
}

// 查询积分流水详细
export function getPointLog(pointLogId) {
  return request({
    url: '/admin/pointLog/' + pointLogId,
    method: 'get'
  })
}

// 新增积分流水
export function addPointLog(data) {
  return request({
    url: '/admin/pointLog',
    method: 'post',
    data: data
  })
}

// 修改积分流水
export function updatePointLog(data) {
  return request({
    url: '/admin/pointLog',
    method: 'put',
    data: data
  })
}

// 删除积分流水
export function delPointLog(pointLogId) {
  return request({
    url: '/admin/pointLog/' + pointLogId,
    method: 'delete'
  })
}
