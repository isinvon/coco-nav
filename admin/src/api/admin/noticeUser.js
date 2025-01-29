import request from '@/utils/request'

// 查询通知用户关系列表
export function listNoticeUser(query) {
  return request({
    url: '/admin/noticeUser/list',
    method: 'get',
    params: query
  })
}

// 查询通知用户关系详细
export function getNoticeUser(relationId) {
  return request({
    url: '/admin/noticeUser/' + relationId,
    method: 'get'
  })
}

// 新增通知用户关系
export function addNoticeUser(data) {
  return request({
    url: '/admin/noticeUser',
    method: 'post',
    data: data
  })
}

// 修改通知用户关系
export function updateNoticeUser(data) {
  return request({
    url: '/admin/noticeUser',
    method: 'put',
    data: data
  })
}

// 删除通知用户关系
export function delNoticeUser(relationId) {
  return request({
    url: '/admin/noticeUser/' + relationId,
    method: 'delete'
  })
}
