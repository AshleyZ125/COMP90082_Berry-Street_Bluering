<template>
  <div class="main">
      <nav-header></nav-header>
      <div class="body">
          <div class="panel">
              <div class="title-container"><span class="title">Create an account</span></div>
              <el-form :model="registerForm" ref="registerForm"  label-width="100px" label-position="top" :rules="registerFormRules" style="margin:30px 0 0 50px">
                <el-form-item label="Email" prop="email">
                    <el-input style="width:300px" v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input style="width:300px" type="password" show-password="true" v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item label="Username" prop="username">
                    <el-input style="width:300px" v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="submitForm('registerForm')" style="margin:20px 0 0 100px">Register</el-button>
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
            registerForm:{
                email: '',
                password:'',
                username:''
            },
            registerFormRules:{
                email: [
                    { required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type:'email',message: 'Please enter correct email address', trigger: ['blur','change'] },
                ],
                password: [
                    { required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 8, max: 16, message: 'Password length should between 8 to 16', trigger: ['blur','change'] },
                ],
                username:[
                    { required: true, message: 'Please enter your name', trigger: 'blur' },
                    { min: 2, max: 16, message: 'Your name length should between 2 to 16', trigger: ['blur','change'] },
                ]
            }
            
        }
    },
    name:'register',
    components:{
        NavHeader
    },
    methods:{
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            //todo: axios
          } else {
              this.$message({
                showClose: true,
                message: 'Oops, Please check the information is valid',
                type: 'error'
            });
            return false;
          }
        });
      },
    }

}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .body{
        .panel{
            width: 400px;
            height: 580px;
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