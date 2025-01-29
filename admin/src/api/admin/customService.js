import request from '@/utils/request'

// 查询客服信息列表
export function listCustomService(query) {
  return request({
    url: '/admin/customService/list',
    method: 'get',
    params: query
  })
}

// 查询客服信息详细
export function getCustomService(customServiceId) {
  return request({
    url: '/admin/customService/' + customServiceId,
    method: 'get'
  })
}

// 新增客服信息
export function addCustomService(data) {
  return request({
    url: '/admin/customService',
    method: 'post',
    data: data
  })
}

// 修改客服信息
export function updateCustomService(data) {
  return request({
    url: '/admin/customService',
    method: 'put',
    data: data
  })
}

// 删除客服信息
export function delCustomService(customServiceId) {
  return request({
    url: '/admin/customService/' + customServiceId,
    method: 'delete'
  })
}
