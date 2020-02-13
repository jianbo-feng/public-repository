<template>
  <div class="user">
  <ul>
    <li><router-link to="/user/login">登录</router-link></li>
    <li><router-link to="/user/register">注册</router-link></li>
    <li><router-link to="/user/center">个人中心</router-link></li>
  </ul>
  <router-view/>
  </div>
</template>

<script>
</script>

<style>
  .user {
    font-size: 0.875rem;
    font-color: red;
  }
</style>
