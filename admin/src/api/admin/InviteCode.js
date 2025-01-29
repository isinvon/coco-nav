import request from '@/utils/request'

// 查询邀请码管理列表
export function listInviteCode(query) {
  return request({
    url: '/admin/InviteCode/list',
    method: 'get',
    params: query
  })
}

// 查询邀请码管理详细
export function getInviteCode(inviteCodeId) {
  return request({
    url: '/admin/InviteCode/' + inviteCodeId,
    method: 'get'
  })
}

// 新增邀请码管理
export function addInviteCode(data) {
  return request({
    url: '/admin/InviteCode',
    method: 'post',
    data: data
  })
}

// 修改邀请码管理
export function updateInviteCode(data) {
  return request({
    url: '/admin/InviteCode',
    method: 'put',
    data: data
  })
}

// 删除邀请码管理
export function delInviteCode(inviteCodeId) {
  return request({
    url: '/admin/InviteCode/' + inviteCodeId,
    method: 'delete'
  })
}
