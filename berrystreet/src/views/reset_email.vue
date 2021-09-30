<template>
    <div class = "reset-main">
        <nav-header></nav-header>
        <div class = "reset_container">
            <div class = "resetemail_panel">
                <div class = "title_container"><span class = "title"> Reset Email</span></div>
                <el-form :model="resetEmailForm" status-icon ref="resetEmailForm"  label-width="100px" label-position="top" :rules="resetEmailFormRules" style="margin:30px 0 0 50px">
                    <el-form-item label = "Old Email" prop = "oldEmail">
                        <el-input type = "email" style="width:300px" v-model="resetEmailForm.oldEmail" autocomplete = "off"></el-input>
                    </el-form-item>
                    <el-form-item label = "New Email" prop = "newEmail">
                        <el-input type = "email" style="width:300px" v-model="resetEmailForm.newEmail" autocomplete = "off"></el-input>
                    </el-form-item>
                    <el-form-item style="margin-top:30px">
                        <el-button  @click="submitForm('resetEmailForm')" style="margin-right:30px"> Submit </el-button>
                        <el-button  @click="resetForm('resetEmailForm')">Clear</el-button>
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
            if (value === ''){
                callback(new Error('Please enter new Email address!'));
            } else if (value === this.resetEmailForm.oldEmail){
                callback(new Error('New Email address cannot same with old Email!'))
            } else {
                callback();
            }
        };
        return {
            resetEmailForm:{
                oldEmail:'',
                newEmail:'',
            },
            resetEmailFormRules:{
                oldEmail: [
                    {required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type: "email", message: 'Please check input correct Email', trigger: ['blur','change'] },
                ],
                newEmail: [
                    {required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type: "email", message: 'Please check input correct Email', trigger: ['blur','change'] },
                    {validator: validateEmail, trigger: 'blur'}
                ]
            }
            
        }
    },
    name:'resetEmail',
    components:{
        NavHeader
    },
    mounted(){
        //console.log(this.$route.params.id)
        console.log(this.$cookie.get('userId'))
    },
    methods:{
        resetEmail(){
            let id = Number(this.$cookie.get('userId'))
             this.axios.post('/api/user/superResetEmail',{
                 newEmail:this.resetEmailForm.newEmail,
                 UID:id,
             }).then((res)=>{
                 if(res.status==200){
                     this.$message({
                        message: 'Reset email successfully!',
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
            this.resetEmail();
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
    .reset_container{
        .resetemail_panel{
            width: 400px;
            height: 500px;
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