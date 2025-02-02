import request from '@/utils/request'

// 查询广告操作日志列表
export function listAdvertisementLog(query) {
  return request({
    url: '/admin/advertisementLog/list',
    method: 'get',
    params: query
  })
}

// 查询广告操作日志详细
export function getAdvertisementLog(advertisementLogId) {
  return request({
    url: '/admin/advertisementLog/' + advertisementLogId,
    method: 'get'
  })
}

// 新增广告操作日志
export function addAdvertisementLog(data) {
  return request({
    url: '/admin/advertisementLog',
    method: 'post',
    data: data
  })
}

// 修改广告操作日志
export function updateAdvertisementLog(data) {
  return request({
    url: '/admin/advertisementLog',
    method: 'put',
    data: data
  })
}

// 删除广告操作日志
export function delAdvertisementLog(advertisementLogId) {
  return request({
    url: '/admin/advertisementLog/' + advertisementLogId,
    method: 'delete'
  })
}

// Index
export function indexAdvertisementLog() {
  return request({
    url: '/admin/advertisementLog/index',
    method: 'get',
  })
}
