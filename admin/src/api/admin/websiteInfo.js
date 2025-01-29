import request from '@/utils/request'

// 查询网站信息列表
export function listWebsiteInfo(query) {
  return request({
    url: '/admin/websiteInfo/list',
    method: 'get',
    params: query
  })
}

// 查询网站信息详细
export function getWebsiteInfo(websiteInfoId) {
  return request({
    url: '/admin/websiteInfo/' + websiteInfoId,
    method: 'get'
  })
}

// 新增网站信息
export function addWebsiteInfo(data) {
  return request({
    url: '/admin/websiteInfo',
    method: 'post',
    data: data
  })
}

// 修改网站信息
export function updateWebsiteInfo(data) {
  return request({
    url: '/admin/websiteInfo',
    method: 'put',
    data: data
  })
}

// 删除网站信息
export function delWebsiteInfo(websiteInfoId) {
  return request({
    url: '/admin/websiteInfo/' + websiteInfoId,
    method: 'delete'
  })
}
