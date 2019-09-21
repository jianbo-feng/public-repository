<template>
  <div>{{title}}<br/>
  欣赏图片：<br/>
  <img src="../../assets/hongyadong.jpeg" alt="洪崖洞" @click="imgClick($event)"/>
  <img :src="require('@/assets/hongyadong.jpeg')" alt="洪崖洞" @click="imgClick($event)"/>
  <img :src="imageShenqi" alt="神泣" @click="imgClick($event)"/><br/>
  <img v-bind:key="item.id" v-for="(item,index) in imageArray" v-on:click="imgClick2(index, $event)" :src="item.url"/><br/>
  <img v-bind:key="index + 100" v-for="(item,index) in imageArray2" v-on:click="imgClick2(index, $event)" :src="item.url"/><br/>
  </div>
</template>

<script>
import imgUrl2 from '@/assets/hongyadong.jpeg'
export default {
  name: 'userCenter',
  data () {
    return {
      title: '个人中心区域',
      imageUrl: require('@/assets/hongyadong.jpeg'),
      imageUrl2: imgUrl2,
      imageShenqi: require('@/assets/shenqi.jpeg'),
      imageArray: [
        {
          id: 0,
          title: '神泣',
          url: require('@/assets/shenqi.jpeg')
        },
        {
          id: 1,
          title: '洪崖洞',
          url: require('@/assets/hongyadong.jpeg')
        }
      ],
      imageArray2: [
        {
          id: 10,
          title: '神泣',
          url: require('@/assets/shenqi.jpeg')
        },
        {
          id: 11,
          title: '洪崖洞',
          url: require('@/assets/hongyadong.jpeg')
        }
      ]
    }
  },
  computed: {},
  beforeDestroy () {
    console.log('销毁前：')
  },
  destoryed () {
    console.log('销毁完成')
  },
  beforeCreate () {
    console.log('创建前：')
    console.log(this.$el)
    console.log(this.$data)
  },
  created () {
    console.log('创建完成：')
    console.log(this.$el)
    console.log(this.$data)
  },
  watch: {
    showContent (currVal, oldVal) {
      console.log(currVal, oldVal)
    }
  },
  beforeMount () {
    console.log('挂载前：')
    console.log(this.$el)
    console.log(this.$data)
  },
  mounted () {
    console.log('挂载完成：')
    console.log(this.$el)
    console.log(this.$data)
  },
  beforeUpdate () {
    console.log('=即将更新渲染=')
    // let name = this.$refs.app.innerHTML
    // console.log('name:' + name)
  },
  updated () {
    console.log('==更新成功==')
    // let name = this.$refs.app.innerHTML
    // console.log('name:' + name)
  },
  methods: {
    /**
     * 图片点击事件
     */
    imgClick (event) {
      console.log('e', event)
      console.log(event.target.alt, event.target.src)
    },
    imgClick2 (index, event) {
      console.log(index, event)
      console.log(event.target.alt, event.target.src)
    }
  }
}
</script>

<style>
  div img {
    width: 18.75rem;
    height: auto;
    cursor: pointer;
  }
</style>
