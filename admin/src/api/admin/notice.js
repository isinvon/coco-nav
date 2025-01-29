import request from '@/utils/request'

// 查询通知管理列表
export function listNotice(query) {
  return request({
    url: '/admin/notice/list',
    method: 'get',
    params: query
  })
}

// 查询通知管理详细
export function getNotice(noticeId) {
  return request({
    url: '/admin/notice/' + noticeId,
    method: 'get'
  })
}

// 新增通知管理
export function addNotice(data) {
  return request({
    url: '/admin/notice',
    method: 'post',
    data: data
  })
}

// 修改通知管理
export function updateNotice(data) {
  return request({
    url: '/admin/notice',
    method: 'put',
    data: data
  })
}

// 删除通知管理
export function delNotice(noticeId) {
  return request({
    url: '/admin/notice/' + noticeId,
    method: 'delete'
  })
}
