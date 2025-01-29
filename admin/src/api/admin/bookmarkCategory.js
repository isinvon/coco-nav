import request from '@/utils/request'

// 查询书签分类列表
export function listBookmarkCategory(query) {
  return request({
    url: '/admin/bookmarkCategory/list',
    method: 'get',
    params: query
  })
}

// 查询书签分类详细
export function getBookmarkCategory(bookmarkCategoryId) {
  return request({
    url: '/admin/bookmarkCategory/' + bookmarkCategoryId,
    method: 'get'
  })
}

// 新增书签分类
export function addBookmarkCategory(data) {
  return request({
    url: '/admin/bookmarkCategory',
    method: 'post',
    data: data
  })
}

// 修改书签分类
export function updateBookmarkCategory(data) {
  return request({
    url: '/admin/bookmarkCategory',
    method: 'put',
    data: data
  })
}

// 删除书签分类
export function delBookmarkCategory(bookmarkCategoryId) {
  return request({
    url: '/admin/bookmarkCategory/' + bookmarkCategoryId,
    method: 'delete'
  })
}
