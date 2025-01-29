import request from '@/utils/request'

// 查询VIP类型列表
export function listVipType(query) {
  return request({
    url: '/admin/vipType/list',
    method: 'get',
    params: query
  })
}

// 查询VIP类型详细
export function getVipType(vipTypeId) {
  return request({
    url: '/admin/vipType/' + vipTypeId,
    method: 'get'
  })
}

// 新增VIP类型
export function addVipType(data) {
  return request({
    url: '/admin/vipType',
    method: 'post',
    data: data
  })
}

// 修改VIP类型
export function updateVipType(data) {
  return request({
    url: '/admin/vipType',
    method: 'put',
    data: data
  })
}

// 删除VIP类型
export function delVipType(vipTypeId) {
  return request({
    url: '/admin/vipType/' + vipTypeId,
    method: 'delete'
  })
}
