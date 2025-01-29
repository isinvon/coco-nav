import request from '@/utils/request'

// 查询书签文章列表
export function listBookmarkArticle(query) {
  return request({
    url: '/admin/bookmarkArticle/list',
    method: 'get',
    params: query
  })
}

// 查询书签文章详细
export function getBookmarkArticle(articleId) {
  return request({
    url: '/admin/bookmarkArticle/' + articleId,
    method: 'get'
  })
}

// 新增书签文章
export function addBookmarkArticle(data) {
  return request({
    url: '/admin/bookmarkArticle',
    method: 'post',
    data: data
  })
}

// 修改书签文章
export function updateBookmarkArticle(data) {
  return request({
    url: '/admin/bookmarkArticle',
    method: 'put',
    data: data
  })
}

// 删除书签文章
export function delBookmarkArticle(articleId) {
  return request({
    url: '/admin/bookmarkArticle/' + articleId,
    method: 'delete'
  })
}
