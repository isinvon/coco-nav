import request from '@/utils/request'

// 查询书签操作日志列表
export function listBookmarkLog(query) {
  return request({
    url: '/admin/bookmarkLog/list',
    method: 'get',
    params: query
  })
}

// 查询书签操作日志详细
export function getBookmarkLog(bookmarkLogId) {
  return request({
    url: '/admin/bookmarkLog/' + bookmarkLogId,
    method: 'get'
  })
}

// 新增书签操作日志
export function addBookmarkLog(data) {
  return request({
    url: '/admin/bookmarkLog',
    method: 'post',
    data: data
  })
}

// 修改书签操作日志
export function updateBookmarkLog(data) {
  return request({
    url: '/admin/bookmarkLog',
    method: 'put',
    data: data
  })
}

// 删除书签操作日志
export function delBookmarkLog(bookmarkLogId) {
  return request({
    url: '/admin/bookmarkLog/' + bookmarkLogId,
    method: 'delete'
  })
}

// Index
export function indexBookmarkLog() {
  return request({
    url: '/admin/bookmarkLog/index',
    method: 'get'
  })
}
