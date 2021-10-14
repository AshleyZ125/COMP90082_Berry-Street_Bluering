<template>
  <div class="register-main">
      <nav-header></nav-header>
      <div class="body">
          <div class="panel">
              <div class="title-container"><span class="title">Create an account</span></div>
              <el-form :model="registerForm" ref="registerForm"  label-width="100px" label-position="top" :rules="registerFormRules" style="margin:30px 0 0 50px">
                <el-form-item label="Email" prop="email">
                    <el-input style="width:300px" v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input style="width:300px" type="password" show-password v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item label="Username" prop="username">
                    <el-input style="width:300px" v-model="registerForm.username"></el-input>
                </el-form-item>
                <!-- <el-form-item label="Code" prop="code">
                    <el-input style="width:300px" type="password" show-password v-model="registerForm.code"></el-input>
                </el-form-item> -->
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
            role:'LEC',
            registerForm:{
                email: '',
                password:'',
                username:'',
                code:''
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
                ],
                // code:[
                //     { required: true, message: 'Please enter the code', trigger: 'blur' },
                //     { min: 6, max: 16, message: 'The code should between 6-16', trigger: ['blur','change'] },
                // ],

            }
            
        }
    },
    name:'register',
    components:{
        NavHeader
    },
    methods:{
        register(){
             //console.log(this.registerForm.email,this.registerForm.password,this.registerForm.username,this.registerForm.code)
             this.axios.post('/api/user/register',{
                 role:this.role,
                 email:this.registerForm.email,
                 password:this.registerForm.password,
                 username:this.registerForm.username,
                 code:this.registerForm.code,

             }).then((res)=>{
                 console.log(res)
                 if(res.status==200){
                     this.$message({
                        message: 'Success!!Please Sign in!',
                        showClose: true,
                        duration:2000,
                        type: 'success',
                         onClose:()=>{
                            this.$router.push('/signinLEC')
                         }
                        
                    })
                    
				}
				else{
                    this.$message({
                        message: res.data.msg,
                        type: 'error'
                    })
                }
                
             })
        },
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.register();
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
            height: 670px;
            margin: 80px auto;
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