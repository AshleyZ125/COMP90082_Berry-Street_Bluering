<template>
    <div class = "reset-main">
        <nav-header></nav-header>
        <div class = "container">
            <div class = "reset_panel">
                <div class = "title_container"><span class = "title"> Reset Password</span></div>
                <el-form :model="resetPasswordForm" status-icon ref="resetPasswordForm"  label-width="100px" label-position="top" :rules="resetPasswordFormRules" style="margin:30px 0 0 50px">
                    <el-form-item label = "Old Password" prop = "old_password">
                        <el-input type = "password" style="width:300px" v-model="resetPasswordForm.old_password" autocomplete = "off"></el-input>
                    </el-form-item>

                     <el-form-item label = "New Password" prop = "pass">
                        <el-input type = "password" style="width:300px" v-model="resetPasswordForm.pass" autocomplete = "off"></el-input>
                    </el-form-item>

                    <el-form-item label = "Confirm Password" prop = "checkpass">
                        <el-input type = "password" style="width:300px" v-model="resetPasswordForm.checkpass" autocomplete = "off"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button  @click="submitForm('resetPasswordForm')"> submit </el-button>
                        <el-button  @click="resetForm('resetPasswordForm')">reset</el-button>
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
        var validatePass = (rule, value, callback) =>{
            if (!value){
                callback(new Error(' please enter password'));
            } else if (value === this.resetPasswordForm.old_password){
                callback(new Error('new password cannot same with old password'))
            } else {
                if (this.resetPasswordForm.checkpass !== ' '){
                    this.$refs.resetPasswordForm.validateField('checkPass');
                }
                callback();
            }
        };

        var validatePass2 = (rule, value, callback) =>{
            if (!value ){
                callback(new Error('Please enter password again'));
            } else if (value !==this.resetPasswordForm.pass){
                callback(new Error('passwords are inconsistent'));
            }  else {
                callback();
            }
        };

        return {
            resetPasswordForm:{
                old_password:'',
                new_password:'',
                confirm_password:''
            },
            resetPasswordFormRules:{
                old_password: [
                    {required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 8, max: 16, message: 'Password length should between 8 to 16', trigger: ['blur','change'] },
                ],
                pass:[
                    {required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 8, max: 16, message: 'Password length should between 8 to 16', trigger: ['blur','change'] },
                    {validator: validatePass, trigger: 'blur'}
                ],
                checkpass: [
                    {required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 8, max: 16, message: 'Password length should between 8 to 16', trigger: ['blur','change'] },
                    {validator: validatePass2, trigger: 'blur'}
                ]
            }
            
        }
    },
    name:'reset_password',
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