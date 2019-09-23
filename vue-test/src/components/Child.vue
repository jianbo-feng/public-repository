<template>
    <div class="son">
      <p>{{ sonMsg }}</p>
      <p>子组件接收到内容：{{ psMsg }}</p>
      <!--<input type="text" v-model="user" @change="setUser">-->
      <button @click="setUser">传值</button>
      <button @click="setUser2">传对象</button>
    </div>
  </template>
<script>
export default {
  name: 'son',
  data () {
    return {
      sonMsg: '子组件',
      user: '张三',
      userObj: {
        name: '张三',
        nickname: '小张'
      }
    }
  },
  props: ['psMsg'],
  methods: {
    setUser2 () {
      this.$emit('transfer2', this.userObj) // 将值绑定到transfer2上传递过去
    },
    setUser: function () {
      this.$emit('transfer', this.user)// 将值绑定到transfer上传递过去
    }
  }
}
</script>
