import request from '@/utils/request'

// 查询留言操作日志列表
export function listMessageLog(query) {
  return request({
    url: '/admin/messageLog/list',
    method: 'get',
    params: query
  })
}

// 查询留言操作日志详细
export function getMessageLog(messageLogId) {
  return request({
    url: '/admin/messageLog/' + messageLogId,
    method: 'get'
  })
}

// 新增留言操作日志
export function addMessageLog(data) {
  return request({
    url: '/admin/messageLog',
    method: 'post',
    data: data
  })
}

// 修改留言操作日志
export function updateMessageLog(data) {
  return request({
    url: '/admin/messageLog',
    method: 'put',
    data: data
  })
}

// 删除留言操作日志
export function delMessageLog(messageLogId) {
  return request({
    url: '/admin/messageLog/' + messageLogId,
    method: 'delete'
  })
}
