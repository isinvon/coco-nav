import request from '@/utils/request'

// 查询书签标签列表
export function listBookmarkTag(query) {
  return request({
    url: '/admin/bookmarkTag/list',
    method: 'get',
    params: query
  })
}

// 查询书签标签详细
export function getBookmarkTag(bookmarkTagId) {
  return request({
    url: '/admin/bookmarkTag/' + bookmarkTagId,
    method: 'get'
  })
}

// 新增书签标签
export function addBookmarkTag(data) {
  return request({
    url: '/admin/bookmarkTag',
    method: 'post',
    data: data
  })
}

// 修改书签标签
export function updateBookmarkTag(data) {
  return request({
    url: '/admin/bookmarkTag',
    method: 'put',
    data: data
  })
}

// 删除书签标签
export function delBookmarkTag(bookmarkTagId) {
  return request({
    url: '/admin/bookmarkTag/' + bookmarkTagId,
    method: 'delete'
  })
}
