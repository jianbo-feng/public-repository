<template>
  <div class="index">
  {{msg}}
  <ul>
    <li><router-link to="/HelloWorld">HelloWorld</router-link></li>
    <li><router-link to="/fine">问候</router-link></li>
    <li><router-link to="/Math2">数学计算</router-link></li>
    <li><router-link to="/user">用户</router-link></li>
    <li><router-link to="/trans2">用户</router-link></li>
  </ul>
  <router-view/>
  </div>
</template>

<script>
let vm2
export default {
  name: 'index',
  data () {
    return {
      msg: '欢迎来到Vue',
      p1: 'good luckcy'
    }
  },
  mounted: () => {
    vm2 = this
    console.log(vm2)
  }
}
</script>

<style>
.index {
  font-size: 0.875rem;
  font-color: red;
}
</style>
