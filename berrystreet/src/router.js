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
import CategInfoLEC from './views/categInfoLEC'
import CategPanel from './views/categPanel'
import SelectQuestion from './views/selectQuestion'
import createFeedback from './views/createFeedback'
import editQuizTitle from './views/editQuizTitle'
import personalSetting from './views/personalSetting'
import myLec from './views/mylec'
import  lecDetail from './views/lecDetail'
import uxComment from './views/uxComment'

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
            path:'/personalSetting',
            name:'personalsetting',
            component:personalSetting,
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
            path:'/mylec',
            name:'mylec',
            component:myLec,
        },
        {
            path:'/createquiztitle',
            name:'createquiztitle',
            component:createQuizTitle,
        },
        {
            path:'/editquiztitle/:id',
            name:'editquiztitle',
            component:editQuizTitle,
        },
        {
            path:'/createquestion/:id',
            name:'createquestion',
            component:createQuestion,
        },
        {
            path:'/createfeedback/:id',
            name:'createfeedback',
            component:createFeedback,
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
        },
        {
            path:'/selectQuestion',
            name:'selectQuestion',
            component: SelectQuestion,
        },
        {
            path:'/lecDetail/:name/:id',
            name:'lecdetail',
            component: lecDetail,
        },
        {
            path:'/uxcomment',
            name:'uxcomment',
            component: uxComment,
        }
        
        
    ]
});
