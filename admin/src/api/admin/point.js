import request from '@/utils/request'

// 查询积分账户列表
export function listPoint(query) {
  return request({
    url: '/admin/point/list',
    method: 'get',
    params: query
  })
}

// 查询积分账户详细
export function getPoint(pointId) {
  return request({
    url: '/admin/point/' + pointId,
    method: 'get'
  })
}

// 新增积分账户
export function addPoint(data) {
  return request({
    url: '/admin/point',
    method: 'post',
    data: data
  })
}

// 修改积分账户
export function updatePoint(data) {
  return request({
    url: '/admin/point',
    method: 'put',
    data: data
  })
}

// 删除积分账户
export function delPoint(pointId) {
  return request({
    url: '/admin/point/' + pointId,
    method: 'delete'
  })
}
