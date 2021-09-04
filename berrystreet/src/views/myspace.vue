<template>
  <div class="space-main">
      <space-header></space-header>
      <div class="drop-div">
          <el-dropdown style="cursor: pointer;float:right;margin-right:25px;margin-top:20px;font-weight:bold;font-size:20px">
            <span class="el-dropdown-link">
                Settings<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><span @click="gotoReset()">Reset Password</span></el-dropdown-item>
                <el-dropdown-item><span @click="gotoResetEmail()">Reset Email</span></el-dropdown-item>
                <el-dropdown-item><a href="/">Account Settings</a></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
      </div>
      <div class="space-container">
          <div class="card" id="quiz" @click="gotoQuiz()">
                <span>My Quizzes</span>
                <img src="./../assets/student.png" alt="">
          </div>
          <div class="card" id="lecs" @click="gotoSignin()">
              <span>My LECs</span>
              <img src="./../assets/teacher.png" alt="" id="teacher">
          </div>
          <div class="card" id="experience" @click="gotoSignin()">
              <span>User Experience Comments</span>
              <img src="./../assets/teacher.png" alt="" id="teacher">
          </div>
      </div>
  </div>
</template>

<script>
import SpaceHeader from './../components/SpaceHeader.vue'
export default {
    name:'myspace',
    data(){
        return{
           userId:''
        }
    },
    components:{
        SpaceHeader
    },
    methods:{
        // getId(){
        //     this.userId = this.$cookie.get('userId')
        // },
        gotoReset(){
            this.$router.push('/resetPassword')
            // this.$router.push({
            //     name: 'resetPassword',
            //     params: {
            //         id: this.userId
            //     }
            // })
        },
        gotoResetEmail(){
            this.$router.push('/resetEmail')
        }
    },
    mounted(){
        // this.getId();
    }
    
}
</script>

<style lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .drop-div{
        float: right;
        height: 50px;
        width: 100%;
        
    }

.space-container{
    display: flex;
    padding: 7% 0;
    width: 100%;
    .card{
        width: 350px;
        height: 300px;
        border-radius: 15px;
        margin: 0 auto;
        text-align: center;
        padding: 5% 1%;
        color: white;
        position: relative;
        cursor: pointer;
        span{
            font-size: 40px;
            font-family: 'Acme', sans-serif;  
        }
        img{
            width: 150px;
            height: 150px;
            position: absolute;
            bottom: 10px;
            right: 0;
            left: 0;
            margin: auto;
        }
        #teacher{
            width: 120px;
            height: 120px;
            bottom: 25px;
        }
    }
    #quiz{
        margin-right: 50px;
        background-image: linear-gradient(120deg, #f6d365 0%, #fda085 100%);
    }
    #lecs{
        margin-right: 50px;
        background-image: linear-gradient(60deg, #96deda 0%, #50c9c3 100%);
    }
    #experience{
        background-image: linear-gradient(120deg, #89f7fe 0%, #66a6ff 100%);
    }

    
}
</style>