<template>
  <div class="sign-main">
      <nav-header></nav-header>
      <div class="body">
          <div class="signin-panel">
              <div class="title-container"><span class="title">Sign In</span></div>
              <el-form :model="signinForm" ref="signinForm" :rules="signinFormRules" hide-required-asterisk label-width="100px" label-position="top" style="margin:30px 0 0 50px">
                <el-form-item label="Email" prop="email">
                    <el-input style="width:300px" v-model="signinForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input style="width:300px" type="password" v-model="signinForm.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="submitForm('signinForm')" style="margin:20px 0 0 110px">Log In</el-button>
                </el-form-item>
            </el-form>
            <span class="hyperlink" @click="gotoReset()" style="margin-left:50px">Forgot my password</span>
            <span class="hyperlink" @click="gotoRegister()" style="margin-left:70px">Create an account</span>
          </div>
          
      </div>
  </div>
</template>

<script>
import NavHeader from './../components/NavHeader.vue'
export default {
    data() {
        
        return {
            signinForm:{
                email: '',
                password:'',
            },
            signinFormRules: {
                email: [
                    { required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type:'email',message: 'Please enter correct email address', trigger: ['blur','change'] },
                ],
                password: [
                    { required: true, message: 'Please enter your password', trigger: 'blur' },
                   
                ],
            },
            userId:''
        }
    },
    name:'signinLEC',
    components:{
        NavHeader
    },
    methods:{
        // gotoReset(){
        //     this.$router.push('/')
        // },
        gotoRegister(){
            this.$router.push('/registerLEC')
        },
        signin(){
             console.log(this.signinForm.email,this.signinForm.password)
             this.axios.post('/api/user/login',{
                 email:this.signinForm.email,
                 password:this.signinForm.password
             }).then((res)=>{
                 //console.log(res)
                 if(res.data.status==0){
                     console.log(res.data.data.username)
                     //console.log(res.data.data.uid)
                     this.userId=res.data.data.uid;
                     this.$cookie.set('userId',res.data.data.uid,{expires: '1M'});
                     this.$cookie.set('userName',res.data.data.username,{expires: '1M'});
                     this.$store.dispatch('saveUserName',res.data.data.username)

                     this.$router.push({
                         name: 'myspaceLEC',
                         params: {
                            userId: this.userId
                         }
                     }) // go to my spaceLEC  
                     
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
            this.signin()
          } else {
              this.$message({
                showClose: true,
                message: 'Oops, Please check your email and password are valid',
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
        .signin-panel{
            width: 400px;
            height: 500px;
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
