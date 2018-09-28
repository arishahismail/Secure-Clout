import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import FileView from '@/components/FileView'
import Home from '@/components/Home'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/fileview',
      name: 'FileView',
      component: FileView
    },
  ]
})
