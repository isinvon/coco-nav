import request from '@/utils/request'

// 查询友情链接列表
export function listFriendlink(query) {
  return request({
    url: '/admin/friendlink/list',
    method: 'get',
    params: query
  })
}

// 查询友情链接详细
export function getFriendlink(friendlinkId) {
  return request({
    url: '/admin/friendlink/' + friendlinkId,
    method: 'get'
  })
}

// 新增友情链接
export function addFriendlink(data) {
  return request({
    url: '/admin/friendlink',
    method: 'post',
    data: data
  })
}

// 修改友情链接
export function updateFriendlink(data) {
  return request({
    url: '/admin/friendlink',
    method: 'put',
    data: data
  })
}

// 删除友情链接
export function delFriendlink(friendlinkId) {
  return request({
    url: '/admin/friendlink/' + friendlinkId,
    method: 'delete'
  })
}
