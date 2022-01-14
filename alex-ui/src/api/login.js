import request from '../utils/request'

export function login(data){
  console.log('start login')
  return request({
    url:'/login',
    headers:{
      isToken:false
    },
    method:'post',
    data:data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/sysuser/info',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captcha?time='+new Date(),
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}
