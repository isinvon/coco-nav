import request from '@/utils/request'

// 查询用户登录日志列表
export function listUserLoginLog(query) {
  return request({
    url: '/admin/userLoginLog/list',
    method: 'get',
    params: query
  })
}

// 查询用户登录日志详细
export function getUserLoginLog(userLoginLogId) {
  return request({
    url: '/admin/userLoginLog/' + userLoginLogId,
    method: 'get'
  })
}

// 新增用户登录日志
export function addUserLoginLog(data) {
  return request({
    url: '/admin/userLoginLog',
    method: 'post',
    data: data
  })
}

// 修改用户登录日志
export function updateUserLoginLog(data) {
  return request({
    url: '/admin/userLoginLog',
    method: 'put',
    data: data
  })
}

// 删除用户登录日志
export function delUserLoginLog(userLoginLogId) {
  return request({
    url: '/admin/userLoginLog/' + userLoginLogId,
    method: 'delete'
  })
}
