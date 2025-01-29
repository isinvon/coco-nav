import request from '@/utils/request'

// 查询用户浏览历史列表
export function listBrowseHistory(query) {
  return request({
    url: '/admin/browseHistory/list',
    method: 'get',
    params: query
  })
}

// 查询用户浏览历史详细
export function getBrowseHistory(browseHistoryId) {
  return request({
    url: '/admin/browseHistory/' + browseHistoryId,
    method: 'get'
  })
}

// 新增用户浏览历史
export function addBrowseHistory(data) {
  return request({
    url: '/admin/browseHistory',
    method: 'post',
    data: data
  })
}

// 修改用户浏览历史
export function updateBrowseHistory(data) {
  return request({
    url: '/admin/browseHistory',
    method: 'put',
    data: data
  })
}

// 删除用户浏览历史
export function delBrowseHistory(browseHistoryId) {
  return request({
    url: '/admin/browseHistory/' + browseHistoryId,
    method: 'delete'
  })
}
