import request from '@/utils/request'

// 查询广告管理列表
export function listAdvertisement(query) {
  return request({
    url: '/admin/advertisement/list',
    method: 'get',
    params: query
  })
}

// 查询广告管理详细
export function getAdvertisement(advertisementId) {
  return request({
    url: '/admin/advertisement/' + advertisementId,
    method: 'get'
  })
}

// 新增广告管理
export function addAdvertisement(data) {
  return request({
    url: '/admin/advertisement',
    method: 'post',
    data: data
  })
}

// 修改广告管理
export function updateAdvertisement(data) {
  return request({
    url: '/admin/advertisement',
    method: 'put',
    data: data
  })
}

// 删除广告管理
export function delAdvertisement(advertisementId) {
  return request({
    url: '/admin/advertisement/' + advertisementId,
    method: 'delete'
  })
}

// Index
export function indexAdvertisement() {
  return request({
    url: '/admin/advertisement/index',
    method: 'get'
  })
}
