<template>
  <div>
    <div>
      <p>{{title}}</p>
      <button @click="show = !show">Toggle render</button>
    </div>
    <transition name="slide-fade">
      <p v-if="show">hello</p>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'transition',
  data () {
    return {
      title: '过渡 & 动画',
      show: true
    }
  }
}
</script>

<style>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s
  }
  .fade-enter, .fade-leave-active {
    opacity: 0
  }
</style>
