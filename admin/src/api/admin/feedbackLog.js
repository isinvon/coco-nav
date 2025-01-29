import request from '@/utils/request'

// 查询反馈处理日志列表
export function listFeedbackLog(query) {
  return request({
    url: '/admin/feedbackLog/list',
    method: 'get',
    params: query
  })
}

// 查询反馈处理日志详细
export function getFeedbackLog(feedbackLogId) {
  return request({
    url: '/admin/feedbackLog/' + feedbackLogId,
    method: 'get'
  })
}

// 新增反馈处理日志
export function addFeedbackLog(data) {
  return request({
    url: '/admin/feedbackLog',
    method: 'post',
    data: data
  })
}

// 修改反馈处理日志
export function updateFeedbackLog(data) {
  return request({
    url: '/admin/feedbackLog',
    method: 'put',
    data: data
  })
}

// 删除反馈处理日志
export function delFeedbackLog(feedbackLogId) {
  return request({
    url: '/admin/feedbackLog/' + feedbackLogId,
    method: 'delete'
  })
}
