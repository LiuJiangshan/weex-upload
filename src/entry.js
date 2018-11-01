/* global Vue */

/* weex initialized here, please do not move this line */
const router = require('./router')
const app = require('@/app.vue')
/* eslint-disable no-new */
new Vue(Vue.util.extend({el: '#root', router}, app))
router.push('/')
