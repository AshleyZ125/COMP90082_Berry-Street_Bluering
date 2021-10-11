<template>
    <div class = "personal">
        <nav-header></nav-header>
        <div class = "personal_container">
            <div class = "reset_panel">
                <div class = "title_container"><span class = "title"> Personal Settings</span></div>
                <el-form :model="resetInfoForm" status-icon ref="resetInfoForm"  label-width="100px" label-position="top" :rules="resetInfoFormRules" style="margin:30px 0 0 50px">
                    <el-form-item label = "New Username" prop = "new_username">
                        <el-input style="width:300px" v-model="resetInfoForm.new_username" autocomplete = "off"></el-input>
                    </el-form-item>
                    <el-form-item style="margin-top:70px">
                        <el-button  @click="submitForm('resetInfoForm')">Submit</el-button>
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
            userId:'',
            resetInfoForm:{
                new_username:''
            },
            resetInfoFormRules:{
                new_username: [
                    { required: true, message: 'Please input your new username', trigger: 'blur' },
                    { min: 1, max: 20, message: 'Length should be 1 to 20', trigger: 'blur' }
                ],
            }
            
        }
    },
    name:'personalsetting',
    components:{
        NavHeader
    },
    mounted(){
        console.log(this.$cookie.get('userId'))
    },
    methods:{
        resetUsername(){
            let id = Number(this.$cookie.get('userId'))
             this.axios.post('/api/user/superResetPassword',{
                 oldPassword:this.resetPasswordForm.old_password,
                 newPassword:this.resetPasswordForm.new_password,
                 UID:id,

             }).then((res)=>{
                 if(res.data.status==200){
                     this.$message({
                        message: 'Reset password successfully!',
                        type: 'success'
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
            this.resetUsername();
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
    .personal_container{
        .reset_panel{
            width: 400px;
            height: 400px;
            margin: 100px auto;
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