import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/home'
import Signin from './views/signin'
import Register from './views/register'
import Myspace from './views/myspace'
import resetPassword from './views/reset_password'
import resetEmail from './views/reset_email'

Vue.use(Router);
export default new Router({
    mode:'history',
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
        },
        {
            path:'/myspace',
            name:'myspace',
            component:Myspace,
        },
        {
            path:'/resetPassword',
            name:'resetPassword',
            component:resetPassword,
        },
        {
            path:'/resetEmail',
            name:'resetEmail',
            component:resetEmail,
        },
        
    ]
});
