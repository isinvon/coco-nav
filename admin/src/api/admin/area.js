import request from '@/utils/request'

// 查询地区信息列表
export function listArea(query) {
  return request({
    url: '/admin/area/list',
    method: 'get',
    params: query
  })
}

// 查询地区信息详细
export function getArea(areaId) {
  return request({
    url: '/admin/area/' + areaId,
    method: 'get'
  })
}

// 新增地区信息
export function addArea(data) {
  return request({
    url: '/admin/area',
    method: 'post',
    data: data
  })
}

// 修改地区信息
export function updateArea(data) {
  return request({
    url: '/admin/area',
    method: 'put',
    data: data
  })
}

// 删除地区信息
export function delArea(areaId) {
  return request({
    url: '/admin/area/' + areaId,
    method: 'delete'
  })
}
