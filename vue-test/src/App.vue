<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view/>
    <!-- <Fine></Fine>-->
    <Math2 :reportName="greeting" :url="greeting"></Math2>
  </div>
</template>

<script>
// 引入组件以使用：<Math2></Math2>
import Fine from './components/Fine.vue'
import Math2 from './components/Math.vue'
export default {
  data () {
    return {
      msg: '欢迎来到菜鸟乐园',
      greeting: '你好'
    }
  },
  components: {
    Fine,
    Math2
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
