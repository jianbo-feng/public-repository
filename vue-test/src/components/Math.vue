<!-- 数学计算 -->
<template>
  <div class="mathClass">
    {{5 + 5}}<br/>
    {{ok ? 'YES' : 'NO'}} <br/>
    字符串反转: {{mssage.split('').reverse().join('')}}
    <div v-bind:id="'list-' + id">菜鸟教程</div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      ok: true,
      mssage: 'We are very happy!',
      id: 1
    }
  }
}
</script>

<style>
  .mathClass {
    font-size: 0.875rem;
    font-color: green;
  }
</style>
