import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Fine from '@/components/Fine'
import Index from '@/components/Index'
import Trans2 from '@/components/Trans'
Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/fine',
      name: 'fine3',
      component: Fine
    },
    {
      path: '/trans2',
      name: 'trans2',
      component: Trans2
    },
    {
      path: '/Math2',
      name: 'math2',
      component: () => import('@/components/Math')
    },
    {
      path: '/user',
      component: () => import('@/components/user/Index'),
      children: [
        {
          path: 'login',
          component: () => import('@/components/user/Login')
        },
        {
          path: 'register',
          component: () => import('@/components/user/Register')
        },
        {
          path: 'center',
          component: () => import('@/components/user/Center')
        }
      ]
    }
  ]
})
