<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="rules" class="login-form" status-icon>
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名" type="text">
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" type="password">
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%" type="text">
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button :loading="loading" size="medium" style="width:100%;" type="primary" @click="submitForm">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>



<script>
import { getCodeImg,login } from "@/api/login";
import Cookies from "js-cookie";
import { setToken} from "../utils/auth";

export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: 'admin',
        password: 'admin123',
        rememberMe: true,
        code: '',
        uuid:''
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
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    submitForm() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          login(this.loginForm).then(resp =>{
            if(resp){
              this.loading = false;
              setToken(resp.token);
              this.$router.replace('/home')
            }
          })
        } else {
          this.$message.error('请输入所有字段')
          return false
        }
      })
    },
    getCode(){
      getCodeImg().then(res => {
        this.codeUrl = "data:image/gif;base64," + res.data.img;
        this.loginForm.uuid = res.uuid;
      });
    },
    getCookie(){

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
