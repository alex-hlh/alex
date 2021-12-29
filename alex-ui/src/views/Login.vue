<template>
  <div class="login">
    <el-form :rules="rules" :model="loginForm" status-icon ref="loginForm" class="login-form">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" auto-complete="off" v-model="loginForm.username" placeholder="请输入用户名">
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" auto-complete="off" v-model="loginForm.password" placeholder="请输入密码">
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input type="text" auto-complete="off" v-model="loginForm.code" placeholder="验证码" style="width: 63%">
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" class="login-code-img"/>
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button :loading="loading" @click="submitForm" size="medium" type="primary" style="width:100%;">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: 'admin',
        password: 'admin123',
        rememberMe: true,
        code: ''
      },
      loading: false,
      codeUrl: '',
      rules: {
        username: [{
          required: true,
          message: '请输入用户名',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }],
        code: [{
          required: true,
          trigger: 'change',
          message: '验证码不能为空'
        }]
      }

    }
  },
  methods: {
    submitForm () {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          alert(111)
        } else {
          this.$message.error('请输入所有字段')
          return false
        }
      })
    }
  }
}
</script>

<style>

.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url(../assets/back.jpg);
  background-size: cover;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 12px;
  background: #ffffff;
  width: 350px;
  padding: 25px 25px 5px 25px;
  box-shadow: #2c2b2b;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;


}

.login-code-img {
  cursor: pointer;
  vertical-align: middle;
}

.login-code-img {
  height: 38px;
}
</style>
