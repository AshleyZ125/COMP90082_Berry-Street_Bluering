import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/home'
import Signin from './views/signin'
import Register from './views/register'
import Reset_password from './views/reset_password'
import CategInfoLEC from './views/categInfoLEC'
import CategPanel from'./views/categPanel'

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
        },
        {
            path:'/reset_password',
            name:'reset_password',
            component: Reset_password,
        },
        {
            path:'/categInfoLEC',
            name:'categInfoLEC',
            component: CategInfoLEC,
        },
        {
            path:'/categPanel',
            name:'categPanel',
            component: CategPanel,
        }
    ]
});
