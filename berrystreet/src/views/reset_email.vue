<template>
    <div class = "reset-main">
        <nav-header></nav-header>
        <div class = "container">
            <div class = "reset_panel">
                <div class = "title_container"><span class = "title"> Reset Email</span></div>
                <el-form :model="resetEmailForm" status-icon ref="resetEmailForm"  label-width="100px" label-position="top" :rules="resetEmailFormRules" style="margin:30px 0 0 50px">
                    <el-form-item label = "New Email" prop = "newEmail">
                        <el-input type = "email" style="width:300px" v-model="resetEmailForm.newEmail" autocomplete = "off"></el-input>
                    </el-form-item>

                     <el-form-item label = "Confirm Email" prop = "confirmEmail">
                        <el-input type = "emial" style="width:300px" v-model="resetEmialForm.confirmEmail" autocomplete = "off"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button  @click="submitForm('resetEmailForm')"> submit </el-button>
                        <el-button  @click="resetForm('resetEmailForm')">reset</el-button>
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
        var validateEmail = (rule, value, callback) =>{
            if (!value){
                callback(new Error(' please enter email'));
            }  else {
                if (this.resetEmailForm.newEmail !== ' '){
                    this.$refs.resetEmailForm.validateField('confirmEmail');
                }
                callback();
            }
        };

        return {
            resetPasswordForm:{
                newEmail:'',
                confirmEmail:'',
            },
            resetEmailFormRules:{
                newEmail: [
                    {required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type = "email", message: 'Email format wrong', trigger: ['blur','change'] },
                ],
                
                confirmEmail: [
                    {required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type = "email", message: 'Email format wrong', trigger: ['blur','change'] },
                    {validator: validateEmail, trigger: 'blur'}
                ]
            }
            
        }
    },
    name:'resetEmail',
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
      resetForm(formName){
          this.$refs[formName].resetFields();
      }
    }
}
</script>


<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .container{
        .reset_panel{
            width: 400px;
            height: 580px;
            margin: 150px auto;
            border-radius: 15px;
            background-color: #50A7C2;
            .title_container{
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