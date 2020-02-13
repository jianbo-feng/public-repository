<template id="news">
    <div>
        <h3>{{title}}</h3>
        <ul class="home">
            <li v-for="(data, index) in newsList">
                {{data}}
            </li>
        </ul>
    </div>
</template>
<style scoped>
    .home li {
        display: block;
    }
</style>
<script>
    export default {
        name: 'news',
        data() {
            return {
                title: '我是测试页面',
                newsList: [
                    '我是第一条新闻',
                    '我是第二条新闻',
                    '我是第三条新闻'
                ]
            }
        }
    }
</script>