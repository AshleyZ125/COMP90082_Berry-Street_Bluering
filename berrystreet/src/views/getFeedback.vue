<template>
<div class="feedback">
    <nonText-header></nonText-header>
    <el-button @click="exit" style = "float:left;margin-left:25px;margin-top:25px;background-color:lightblue;font-size:35px"> Exit quiz</el-button>
    <h2 class="feedback-title" >Feedback</h2>
    <h3 class="feedback-content" >{{this.feedback}}</h3>
    <br />
    <h2 class="reflection-title" >Reflection Diary</h2>
    <h3 class="reflection-words" >Write down your thoughts and feelings. What are your strengths? What can you do to improve?</h3>
    <div class="input-reflection">
        <el-input
            type="textarea"
            :autosize="{ minRows: 4, maxRows: 6}"
            placeholder="input your reflection..."
            v-model="reflectionDiary">
        </el-input>
    </div>
    <el-button id="save" @click="save" style = "float:left;margin-left:200px;margin-top:25px;background-color:lightblue;font-size:35px"> Save </el-button>
    <el-button id="share" @click="share" style = "float:right;margin-right:200px;margin-top:25px;background-color:lightblue;font-size:35px"> Share </el-button>
    <el-dialog
        title="Please enter the recipient's email you would like to share your feedback to!"
        :visible.sync="shareVisible"
        style="font-family: 'Acme', sans-serif; text-align: center; font-size: 25px;"
        width="40%">
        <span style="font-size: 15px; ">You may also enter your own email address to have it sent to yourself too!</span>
        <div style="width: 400px;
            height: 200px;
            margin: 50px auto;
            border-radius: 15px;
            background-color: #50A7C2;">
            <el-form :model="shareForm" ref="shareForm" label-width="100px" label-position="centre" :rules="shareFormRules" style="font-family: 'Acme', sans-serif; text-align: center; font-size: 25px;">
                <el-form-item label="Email: " prop="email">
                    <el-input style="width:200px; margin-top:20px; " v-model="shareForm.email"></el-input>
                </el-form-item>
            </el-form>
            <div>
                <el-checkbox v-model="checked" style="margin-left:10px;margin-top:10px;color: white" >Include my reflection diary in sharing</el-checkbox>
            </div>
            <el-button @click="shareReflection" style="font-family: 'Acme', sans-serif; margin-top:20px;">Share</el-button>
        </div>
    </el-dialog>
    <el-dialog
        title="Please enter the recipient's email you would like to share your feedback to!"
        :visible.sync="shareResultVisible"
        style="font-family: 'Acme', sans-serif; text-align: center; font-size: 25px;"
        width="40%">
        <span style="font-size: 15px; ">You may also enter your own email address to have it sent to yourself too!</span>
        <div style="width: 400px;height: 200px;margin: 50px auto; border-radius: 15px;background-color: #50A7C2;">
            <br/>
            <br/>
            <div>
                <span style="font-size: 20px; color: white">The feedback has been sent to {{shareForm.email}}! </span>
            </div>
            <el-button @click="shareReturn" style="font-family: 'Acme', sans-serif; margin-top:30px;">Return</el-button>
        </div>
    </el-dialog>
    <el-dialog
        title="It looks like you haven't signed in, please sign in to save you feedback"
        :visible.sync="registerVisible"
        style="font-family: 'Acme', sans-serif; text-align: center; font-size: 25px;"
        width="40%">
        <div style="margin-top:10px;width: 400px; height: 500px; margin: 80px auto; border-radius: 15px; background-color: #50A7C2;">
            <span style="font-size: 35px;margin-top:20px;color:white">Register</span>
            <el-form :model="registerForm" ref="registerForm"  label-width="100px" label-position="centre" :rules="registerFormRules" >
                <el-form-item label="Email" prop="email">
                    <el-input style="width:200px; margin-top:20px;" v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input style="width:200px; margin-top:20px;" type="password" show-password v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item label="Username" prop="username">
                    <el-input style="width:200px; margin-top:20px;" v-model="registerForm.username"></el-input>
                </el-form-item>
            </el-form>
            <el-button @click="submitForm('registerForm')" style="font-family: 'Acme', sans-serif; margin-top:20px;">Register</el-button>
            <br/>
            <br/>
            <div>
                <span style="font-family: 'Acme', sans-serif; margin-top:20px; color: white">Already have an account?</span>
            </div>
            <el-button @click="signinPage" style="font-family: 'Acme', sans-serif; margin-top:20px;">Log in</el-button>
        </div>
    </el-dialog>
    <el-dialog
        title="It looks like you haven't signed in, please sign in to save you feedback"
        :visible.sync="signinVisible"
        style="font-family: 'Acme', sans-serif; text-align: center; font-size: 25px;"
        width="40%">
        <div style="margin-top:10px; width: 400px; height: 400px; margin: 80px auto; border-radius: 15px; background-color: #50A7C2;">
            <span style="font-size: 35px;margin-top:20px;color:white">Sign in</span>
            <el-form :model="signinForm" ref="signinForm"  label-width="100px" label-position="centre" :rules="signinFormRules" >
                <el-form-item label="Email" prop="email">
                    <el-input style="width:200px; margin-top:20px;" v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input style="width:200px; margin-top:20px;" type="password" show-password v-model="signinForm.password"></el-input>
                </el-form-item>
            </el-form>
            <el-button @click="submitForm('signinForm')" style="font-family: 'Acme', sans-serif; margin-top:20px;">Log in</el-button>
            <br/>
            <br/>
            <div>
                <span style="font-family: 'Acme', sans-serif; margin-top:20px; color: white">Don't have an account?</span>
            </div>
            <el-button @click="registerPage" style="font-family: 'Acme', sans-serif; margin-top:20px;">Register</el-button>
        </div>
        <div>
            <span style="font-size: 15px; margin-top:10px;">Alternatively, if you prefer not creating an account, you can share this feedback to your own personal email!</span>
        </div>
        <el-button @click="share" style="font-family: 'Acme', sans-serif; margin-top:20px; background-color:lightblue">Share</el-button>
    </el-dialog>
    <feedback-footer></feedback-footer>
</div>
</template>
<script>
import NonTextHeader from './../components/NonTextHeader.vue'
import FeedbackFooter from './../components/FeedbackFooter.vue'
export default {
    name:'getFeedback',
    components:{
        NonTextHeader,
        FeedbackFooter
    },
    data(){
        return{
            feedback:"",
            reflectionDiary:"",
            // share
            shareVisible: false,
            // shareEmail:"",
            checked:false,
            shareForm:{
                email:"",
            },
            shareFormRules:{
                email:[
                    { required: true, message: 'Please enter the email you want to share', trigger: 'blur' },
                    { type:'email',message: 'Please enter correct email address', trigger: ['blur','change'] },
                ],
            },
            // share result
            shareResultVisible: false,
            // register
            registerForm:{
                email: "",
                password:"",
                username:"",
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
            },
            registerVisible: false,
            // sign in
            signinForm:{
                email: "",
                password:"",
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
            signinVisible: false,
        }
    },
    mounted(){
        this.feedback = this.$route.params.feedback;
        // this.feedback="At this level,....";
    },
    methods:{
        exit(){
            alert("exit");
        },
        save(){
            this.registerVisible=true;
        },
        share(){
            this.shareVisible=true;
            this.signinVisible=false;
        },
        login(){
            alert("login")
        },
        shareReflection(){
            if(this.shareForm.email!=""){
                if(this.checked){
                    // axios share
                    console.log("分享feedback+diary");
                    console.log(this.feedback);
                    console.log(this.reflectionDiary);
                }else{
                    // axios share
                    console.log("分享feedback");
                    console.log(this.reflectionDiary);
                }
                this.shareResultVisible=true;
                this.shareVisible=false;
            }else{
                alert("please input email!");
            }
        },
        shareReturn(){
            this.shareVisible=true;
            this.shareResultVisible=false;
            this.shareForm.email="";
            this.checked=false;
        },
        registerPage(){
            this.registerVisible=true;
            this.signinVisible=false;
        },
        signinPage(){
            this.signinVisible=true;
            this.registerVisible=false;
        },
        register(){
             //console.log(this.registerForm.email,this.registerForm.password,this.registerForm.username,this.registerForm.code)
             this.axios.post('/api/user/register',{
                 role:'LEC',
                 email:this.registerForm.email,
                 password:this.registerForm.password,
                 username:this.registerForm.username,

             }).then((res)=>{
                 console.log(res)
                 if(res.status==200){
                     this.$message({
                        message: 'Success!!Please Sign in!',
                        showClose: true,
                        duration:2000,
                        type: 'success',
                        //  onClose:()=>{
                        //     this.$router.push('/signin')
                        //  }
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
                     this.$cookie.set('userId',res.data.data.uid,{expires: '1M'});
                     this.$cookie.set('userName',res.data.data.username,{expires: '1M'});
                     this.$store.dispatch('saveUserName',res.data.data.username)

                     this.$router.push('/myspace')
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
                    if(formName=="registerForm"){
                        this.register();
                    }else{
                        this.signin();
                    }
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
.feedback{
    .feedback-title{
        font-family: 'Acme', sans-serif;
        font-size: 40px;
        font-weight: 50;
        text-align: left;
        margin-left: 50px;
        // display: inline-block;
        margin-top: 125px;
    }
    .feedback-content{
        font-family: Arial, Helvetica, sans-serif;
        font-size: 25px;
        font-weight: 50;
        text-align: left;
        margin-left: 50px;
        // display: inline-block;
        //margin-top: 150px;
    }
    .reflection-title{
        font-family: 'Acme', sans-serif;
        font-size: 40px;
        font-weight: 50;
        text-align: left;
        margin-left: 50px;
        // display: inline-block;
        margin-top: 25px;
    }
    .reflection-words{
        font-family: Arial, Helvetica, sans-serif;
        font-size: 25px;
        font-weight: 50;
        text-align: left;
        margin-left: 50px;
        // display: inline-block;
        //margin-top: 150px;
    }
    .input-reflection{
        font-family: 'Acme', sans-serif;  
        font-size: 25px;
        text-align: left;
        margin-left: 50px;
        margin-right: 50px;
        margin-top: 25px;
    }
    .el-form-item__label{
        font-size: 15px;            
        font-family: 'Acme', sans-serif;  
        margin-top:20px;
        color: white;
    }
}
</style>
