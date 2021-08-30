import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/home'
import Signin from './views/signin'
import Register from './views/register'

Vue.use(Router);
export default new Router({
    routes:[
        {
            path:'/',
            name:'home',
            component:Home,
        },
        {
            path:'/signin',
            name:'signin',
            component:Signin,
        },
        {
            path:'/register',
            name:'register',
            component:Register,
        }
    ]
});
