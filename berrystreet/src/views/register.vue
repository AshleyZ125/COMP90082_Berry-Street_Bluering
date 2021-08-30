<template>
  <div class="main">
      <nav-header></nav-header>
      <div class="body">
          <div class="panel">
              <div class="title-container"><span class="title">Create an account</span></div>
              <el-form ref=""  label-width="100px" label-position="top" style="margin:30px 0 0 50px">
                <el-form-item label="Email" >
                    <el-input style="width:300px" v-model="email"></el-input>
                </el-form-item>
                <el-form-item label="Password">
                    <el-input style="width:300px" type="password" v-model="password"></el-input>
                </el-form-item>
                <el-form-item label="Username">
                    <el-input style="width:300px" v-model="username"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="margin:20px 0 0 100px">Register</el-button>
                </el-form-item>
            </el-form>
            
          </div>
          
      </div>
  </div>
</template>

<script>
import NavHeader from './../components/NavHeader.vue'
export default {
    data() {
        return {
        email: '',
        password:'',
        username:''
        }
    },
    name:'register',
    components:{
        NavHeader
    },
    methods:{
        
    }

}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .body{

        .panel{
            width: 400px;
            height: 600px;
            margin: 150px auto;
            border-radius: 15px;
            background-color: #50A7C2;
            .title-container{
                margin-top: 30px;
                text-align: center;
                font-size: 40px;
                font-family: 'Acme', sans-serif;  
                color: white;
                .title{
                    display: inline-block;
                    margin-top: 50px;
                }
            }
            
        }
        .hyperlink{
            font-size: 15px;
            font-family: 'Acme', sans-serif;  
            color: white;
            cursor: pointer;
        }
        //elementUI style customize
        .el-button{
            background-color: #FFFACD;
            font-size: 20px;
            font-family: 'Acme', sans-serif; 
        }
        .el-form-item__label{
            font-size: 25px;
            font-family: 'Acme', sans-serif;  
            color: white;
        }
        
    }
</style>