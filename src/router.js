/* global Vue */
import Router from 'vue-router'
import login from '@/views/login'

Vue.use(Router)

module.exports = new Router({
  routes: [
    {
      path: '/',
      component: login
    }
  ]
})
