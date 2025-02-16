import request from '@/utils/request'

// 查询客户信息列表
export function listCustomer(query) {
  return request({
    url: '/user/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户信息详细
export function getCustomer(id) {
  return request({
    url: '/user/customer/' + id,
    method: 'get'
  })
}

// 新增客户信息
export function addCustomer(data) {
  return request({
    url: '/user/customer',
    method: 'post',
    data: data
  })
}

// 修改客户信息
export function updateCustomer(data) {
  return request({
    url: '/user/customer',
    method: 'put',
    data: data
  })
}

// 删除客户信息
export function delCustomer(id) {
  return request({
    url: '/user/customer/' + id,
    method: 'delete'
  })
}

// 分配用户角色
export function updateCustomerRole(data) {
  return request({
    url: '/user/customer/authRole',
    method: 'put',
    params: data
  })
}

// 用户状态修改
export function changeCustomerStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/user/customer/changeStatus',
    method: 'put',
    data: data
  })
}