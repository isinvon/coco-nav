import request from '@/utils/request'

// 查询设置变更日志列表
export function listSettingLog(query) {
  return request({
    url: '/admin/settingLog/list',
    method: 'get',
    params: query
  })
}

// 查询设置变更日志详细
export function getSettingLog(settingLogId) {
  return request({
    url: '/admin/settingLog/' + settingLogId,
    method: 'get'
  })
}

// 新增设置变更日志
export function addSettingLog(data) {
  return request({
    url: '/admin/settingLog',
    method: 'post',
    data: data
  })
}

// 修改设置变更日志
export function updateSettingLog(data) {
  return request({
    url: '/admin/settingLog',
    method: 'put',
    data: data
  })
}

// 删除设置变更日志
export function delSettingLog(settingLogId) {
  return request({
    url: '/admin/settingLog/' + settingLogId,
    method: 'delete'
  })
}
