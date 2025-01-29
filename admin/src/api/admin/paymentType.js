import request from '@/utils/request'

// 查询支付方式列表
export function listPaymentType(query) {
  return request({
    url: '/admin/paymentType/list',
    method: 'get',
    params: query
  })
}

// 查询支付方式详细
export function getPaymentType(paymentTypeId) {
  return request({
    url: '/admin/paymentType/' + paymentTypeId,
    method: 'get'
  })
}

// 新增支付方式
export function addPaymentType(data) {
  return request({
    url: '/admin/paymentType',
    method: 'post',
    data: data
  })
}

// 修改支付方式
export function updatePaymentType(data) {
  return request({
    url: '/admin/paymentType',
    method: 'put',
    data: data
  })
}

// 删除支付方式
export function delPaymentType(paymentTypeId) {
  return request({
    url: '/admin/paymentType/' + paymentTypeId,
    method: 'delete'
  })
}
