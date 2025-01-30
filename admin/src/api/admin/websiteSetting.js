import request from '@/utils/request'

// 查询系统设置列表
export function listSetting(query) {
  return request({
    url: '/admin/setting/list',
    method: 'get',
    params: query
  })
}

// 查询系统设置详细
export function getSetting(settingId) {
  return request({
    url: '/admin/setting/' + settingId,
    method: 'get'
  })
}

// 新增系统设置
export function addSetting(data) {
  return request({
    url: '/admin/setting',
    method: 'post',
    data: data
  })
}

// 修改系统设置
export function updateSetting(data) {
  return request({
    url: '/admin/setting',
    method: 'put',
    data: data
  })
}

// 删除系统设置
export function delSetting(settingId) {
  return request({
    url: '/admin/setting/' + settingId,
    method: 'delete'
  })
}
