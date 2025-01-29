import request from '@/utils/request'

// 查询用户等级列表
export function listUserGrade(query) {
  return request({
    url: '/admin/userGrade/list',
    method: 'get',
    params: query
  })
}

// 查询用户等级详细
export function getUserGrade(userGradeId) {
  return request({
    url: '/admin/userGrade/' + userGradeId,
    method: 'get'
  })
}

// 新增用户等级
export function addUserGrade(data) {
  return request({
    url: '/admin/userGrade',
    method: 'post',
    data: data
  })
}

// 修改用户等级
export function updateUserGrade(data) {
  return request({
    url: '/admin/userGrade',
    method: 'put',
    data: data
  })
}

// 删除用户等级
export function delUserGrade(userGradeId) {
  return request({
    url: '/admin/userGrade/' + userGradeId,
    method: 'delete'
  })
}
