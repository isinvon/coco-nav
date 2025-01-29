import request from '@/utils/request'

// 查询VIP会员列表
export function listVip(query) {
  return request({
    url: '/admin/vip/list',
    method: 'get',
    params: query
  })
}

// 查询VIP会员详细
export function getVip(vipId) {
  return request({
    url: '/admin/vip/' + vipId,
    method: 'get'
  })
}

// 新增VIP会员
export function addVip(data) {
  return request({
    url: '/admin/vip',
    method: 'post',
    data: data
  })
}

// 修改VIP会员
export function updateVip(data) {
  return request({
    url: '/admin/vip',
    method: 'put',
    data: data
  })
}

// 删除VIP会员
export function delVip(vipId) {
  return request({
    url: '/admin/vip/' + vipId,
    method: 'delete'
  })
}
