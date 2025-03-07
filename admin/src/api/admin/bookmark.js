import request from '@/utils/request'

// 查询书签管理列表
export function listBookmark(query) {
  return request({
    url: '/admin/bookmark/list',
    method: 'post',
    data: query
  })
}

// 查询书签管理详细
export function getBookmark(bookmarkId) {
  return request({
    url: '/admin/bookmark/' + bookmarkId,
    method: 'get'
  })
}

// 新增书签管理
export function addBookmark(data) {
  return request({
    url: '/admin/bookmark',
    method: 'post',
    data: data
  })
}

// 修改书签管理
export function updateBookmark(data) {
  return request({
    url: '/admin/bookmark',
    method: 'put',
    data: data
  })
}

// 删除书签管理
export function delBookmark(bookmarkId) {
  return request({
    url: '/admin/bookmark/' + bookmarkId,
    method: 'delete'
  })
}

// url 爬虫爬取网站信息
export function getUrlInfoByCrawler(url) {
  return request({
    url: '/admin/bookmark/getUrlInfoByCrawler',
    method: 'get',
    params: { url }
  })
}

// Index
export function indexBookmark() {
  return request({
    url: '/admin/bookmark/index',
    method: 'get'
  })
}