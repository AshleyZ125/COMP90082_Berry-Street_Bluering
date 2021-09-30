import Vue from 'vue'
import router from './router'
import elementui from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
//import VueAxios from 'vue-axios'
import VueCookie from 'vue-cookie'
import store from './store'
import App from './App.vue'

Vue.use(elementui)
Vue.prototype.axios = axios
Vue.use(VueCookie)
Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
