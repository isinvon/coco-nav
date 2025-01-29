import request from '@/utils/request'

// 查询反馈类型列表
export function listFeedbackType(query) {
  return request({
    url: '/admin/feedbackType/list',
    method: 'get',
    params: query
  })
}

// 查询反馈类型详细
export function getFeedbackType(feedbackTypeId) {
  return request({
    url: '/admin/feedbackType/' + feedbackTypeId,
    method: 'get'
  })
}

// 新增反馈类型
export function addFeedbackType(data) {
  return request({
    url: '/admin/feedbackType',
    method: 'post',
    data: data
  })
}

// 修改反馈类型
export function updateFeedbackType(data) {
  return request({
    url: '/admin/feedbackType',
    method: 'put',
    data: data
  })
}

// 删除反馈类型
export function delFeedbackType(feedbackTypeId) {
  return request({
    url: '/admin/feedbackType/' + feedbackTypeId,
    method: 'delete'
  })
}
