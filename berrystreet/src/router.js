import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/home'
import Signin from './views/signin'
import Register from './views/register'
import Myspace from './views/myspace'
import resetPassword from './views/reset_password'
import resetEmail from './views/reset_email'
import myQuiz from './views/myquiz'
import createQuizTitle from './views/createQuizTitle'
import createQuestion from './views/createQuestion'

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
        {
            path:'/myquiz',
            name:'myquiz',
            component:myQuiz,
        },
        {
            path:'/createquiztitle',
            name:'createquiztitle',
            component:createQuizTitle,
        },
        {
            path:'/createquestion',
            name:'createquestion',
            component:createQuestion,
        },
        
        
    ]
});
