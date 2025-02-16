import request from '@/utils/request'

// 查询员工信息列表
export function listEmpl(query) {
  return request({
    url: '/user/empl/list',
    method: 'get',
    params: query
  })
}

// 查询员工信息详细
export function getEmpl(id) {
  return request({
    url: '/user/empl/' + id,
    method: 'get'
  })
}

// 新增员工信息
export function addEmpl(data) {
  return request({
    url: '/user/empl',
    method: 'post',
    data: data
  })
}

// 修改员工信息
export function updateEmpl(data) {
  return request({
    url: '/user/empl',
    method: 'put',
    data: data
  })
}

// 删除员工信息
export function delEmpl(id) {
  return request({
    url: '/user/empl/' + id,
    method: 'delete'
  })
}
