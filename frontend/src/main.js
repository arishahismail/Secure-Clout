// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import colors from 'vuetify/es5/util/colors'

Vue.config.productionTip = false

//Vuetify
Vue.use(Vuetify, {
  theme:{
    primary: colors.blueGrey.base,
    secondary: colors.blueGrey.lighten3,
    accent: colors.cyan.darken4,
    error: colors.red.lighten1,
    warning: colors.yellow.lighten1,
    info: colors.blue.lighten2,
    success: colors.green.lighten1
  }
 }),

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
