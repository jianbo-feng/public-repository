<template>
    <div class="fine">
      <h1>{{msg}}</h1>
      <p>{{p1}}</p>
    </div>
</template>

<script>
export default {
  name: 'fine1',
  data () {
    return {
      msg: 'We are very happy!',
      p1: 'good luckcy'
    }
  }
}
</script>

<style scoped>
.fine {
  font-color: red;
  font-size: 24px;
}
</style>
