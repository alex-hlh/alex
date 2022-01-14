<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">后台管理系统</div>
      <el-form
        :model="loginForm"
        :rules="rules"
        ref="loginF"
        label-width="0px"
        class="ms-content"
      >
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="username">
            <template #prepend>
              <el-button icon="el-icon-user"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            placeholder="password"
            v-model="loginForm.password"
          >
            <template #prepend>
              <el-button icon="el-icon-lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%"
            type="text"
          >
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img" />
          </div>
        </el-form-item>
        <el-checkbox
          v-model="loginForm.rememberMe"
          style="margin: 0px 0px 25px 0px"
          >记住密码</el-checkbox
        >
        <el-form-item style="width: 100%">
          <el-button
            :loading="loading"
            size="medium"
            style="width: 100%"
            type="primary"
            @click="submitForm"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ref, reactive, watchEffect } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { login, getCodeImg } from "@/api/login";
import {setToken} from "@/utils/auth"

export default {
  setup() {
    const router = useRouter();
    const loginForm = reactive({
      username: "admin",
      password: "admin123",
      code: "",
      rememberMe:true,
      uuid: "",
    });
    const loading = ref(false);
    const codeUrl = ref("");
    const rules = {
      username: [
        {
          required: true,
          message: "请输入用户名",
          trigger: "blur",
        },
      ],
      password: [
        {
          required: true,
          message: "请输入密码",
          trigger: "blur",
        },
      ],
      code: [
        {
          required: true,
          trigger: "change",
          message: "验证码不能为空",
        },
      ],
    };
    const loginF = ref(null);
    const submitForm = () => {
      loginF.value.validate((valid) => {
        if (valid) {
          loading.value = true;
          login(loginForm).then((resp) => {
              console.log(resp)
            if (resp) {
              loading.value = true;
              setToken(resp.token);
              router.push("/");
            }
          });
        //   ElMessage.success("登录成功");
        //   localStorage.setItem("ms_username", param.username);
        //   router.push("/");
        } else {
          ElMessage.error("请输入所有字段");
          return false;
        }
      });
    };

    const getcode = () => {
        getCodeImg().then((res) => {
        loginForm.codeUrl = "data:image/gif;base64," + res.data.img;
        loginForm.uuid = res.uuid;
      });
    };
    const s = watchEffect(() =>{
        getcode();
    });
    const store = useStore();
    store.commit("clearTags");

    return {
      loginForm,
      rules,
      loginF,
      loading,
      codeUrl,
      getcode,
      submitForm,
    };
  },
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(../assets/img/login-bg.jpg);
  background-size: 100%;
}
.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}
.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.3);
  overflow: hidden;
}
.ms-content {
  padding: 30px 30px;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
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