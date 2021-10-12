<template>
<div class="feedback">
    <quiz-header></quiz-header>
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
                <el-form-item label="Email" prop="rEmail">
                    <el-input style="width:200px; margin-top:20px;" v-model="registerForm.rEmail"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="rPassword">
                    <el-input style="width:200px; margin-top:20px;" type="password" show-password v-model="registerForm.rPassword"></el-input>
                </el-form-item>
                <el-form-item label="Username" prop="rUsername">
                    <el-input style="width:200px; margin-top:20px;" v-model="registerForm.rUsername"></el-input>
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
                <el-form-item label="Email" prop="sEmail">
                    <el-input style="width:200px; margin-top:20px;" v-model="signinForm.sEmail"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="sPassword">
                    <el-input style="width:200px; margin-top:20px;" type="password" show-password v-model="signinForm.sPassword"></el-input>
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
    <el-dialog
        title="Save successfully!"
        :visible.sync="saveResultVisible"
        style="font-family: 'Acme', sans-serif; text-align: center; font-size: 25px;"
        width="40%">
    </el-dialog>
    <feedback-footer></feedback-footer>
</div>
</template>
<script>
import QuizHeader from './../components/QuizHeader.vue'
import FeedbackFooter from './../components/FeedbackFooter.vue'
export default {
    name:'getFeedback',
    components:{
        QuizHeader,
        FeedbackFooter
    },
    data(){
        return{
            currentDate:"",
            UID: -1,
            quizID: '',
            topic: "",
            scores: '',
            result:[],
            feedback:"",
            reflectionDiary:"",
            RID: -1,
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
                rEmail: "",
                rPassword:"",
                rUsername:"",
            },
            registerFormRules:{
                rEmail: [
                    { required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type:'email',message: 'Please enter correct email address', trigger: ['blur','change'] },
                ],
                rPassword: [
                    { required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 8, max: 16, message: 'Password length should between 8 to 16', trigger: ['blur','change'] },
                ],
                rUsername:[
                    { required: true, message: 'Please enter your name', trigger: 'blur' },
                    { min: 2, max: 16, message: 'Your name length should between 2 to 16', trigger: ['blur','change'] },
                ],
            },
            registerVisible: false,
            // sign in
            signinForm:{
                sEmail: "",
                sPassword:"",
            },
            signinFormRules: {
                sEmail: [
                    { required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type:'email',message: 'Please enter correct email address', trigger: ['blur','change'] },
                ],
                sPassword: [
                    { required: true, message: 'Please enter your password', trigger: 'blur' },
                   
                ],
            },
            signinVisible: false,
            saveResultVisible: false,
        }
    },
    mounted(){
        this.UID=this.$route.params.userId;
        this.quizID=this.$route.params.quizId;
        this.topic=this.$route.params.topic;
        this.scores=this.$route.params.scores;
        this.result=this.$route.params.result;
        this.fetchFeedback();
        console.log(this.quizID)
        console.log(this.topic)
        console.log(this.scores)
        console.log(this.result)
    },
    methods:{
        fetchFeedback(){
            // this.feedback="At this level, ..."
            this.axios.get(`/api/quiz/getFeedback/${this.quizID}/${this.scores}`).then((res) => {
                console.log(res.data.data);
                this.feedback = res.data.data.remark;
            })
        },
        exit(){
            if(this.UID==-1){
                this.$router.push({
                    name: 'categPanel',
                    params: {
                        userId: this.UID,
                    }
                })
            }else{
                this.$router.push({
                    name: 'myspaceLEC',
                    params: {
                        userId: this.UID,
                    }
                })
            }
            //  this.$router.push('myspace')
        },
        save(){
            if(this.UID==-1){
                this.registerVisible=true;
            }else{
                this.getTime()
                // let recordVO={"QuizContent": this.result, "rFeedback":this.feedback, "SavedReflection": this.reflectionDiary, "rTopic":this.topic, "UserID":this.UID, "rDate":this.currentDate};
                // let recordVO={"quizContent": this.result, "rFeedback":this.feedback, "savedReflection": this.reflectionDiary, "rTopic":this.topic, "userID":this.UID, "rDate":this.currentDate};
                console.log('/api/record/saveRecord/'+this.RID.toString())
                console.log(this.feedback)
                console.log(this.topic)
                console.log("question 是", this.result)
                this.axios.post('/api/record/saveRecord/'+this.RID.toString(),{
                    // recordVO: recordVO
                    quizContent: this.result,
                    feedback:this.feedback, 
                    savedReflection: this.reflectionDiary, 
                    topic:this.topic, 
                    userID:this.UID
                    // rDate:this.currentDate
                }).then((res)=>{
                    console.log(res)
                    if(res.data.status==0){
                        this.RID=res.data.data
                        this.$message({
                            message: 'Save successfully!',
                            showClose: true,
                            duration:2000,
                            type: 'success',
                            onClose:()=>{
                                this.signinVisible=false 
                                this.saveResultVisible=true
                            }
                        })
                    }else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
            }
        },
        share(){
            this.shareVisible=true;
            this.signinVisible=false;
        },
        shareReflection(){
            if(this.shareForm.email!=""){
                let shareReflection=""
                if(this.checked){
                    shareReflection=this.reflectionDiary
                }
                this.getTime();
                let shareVO={"sender": this.UID, "receiver": this.shareForm.email, "shareReflection": this.reflectionDiary, "quizContent": this.result, "feedback":this.feedback, "topic":this.topic, "userID":this.UID};
                console.log(shareVO)
                this.axios.post('/api/record/saveShare/'+this.RID.toString(),{
                    sender: this.UID,
                    receiver: this.shareForm.email,
                    shareReflection: shareReflection,
                    quizContent: this.result,
                    feedback: this.feedback,
                    topic: this.topic,
                    userID: this.UID
                }).then((res)=>{
                    console.log(res)
                    if(res.data.status==0){
                        this.RID=res.data.data
                        this.$message({
                            message: 'Share successfully!',
                            showClose: true,
                            duration:2000,
                            type: 'success',
                            onClose:()=>{
                                this.signinVisible=false 
                                this.shareVisible=false
                                this.shareResultVisible=true
                            }  
                        })
                    }else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
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
                email:this.registerForm.rEmail,
                password:this.registerForm.rPassword,
                username:this.registerForm.rUsername,

            }).then((res)=>{
                console.log(res)
                if(res.data.status==0){
                    this.$message({
                        message: 'Success!!Please Sign in!',
                        showClose: true,
                        duration:2000,
                        type: 'success',
                        onClose:()=>{
                            this.registerVisible=false
                            this.signinVisible=true
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
        signin(){
            console.log(this.signinForm.sEmail)
            console.log(this.signinForm.sPassword) 
            this.axios.post('/api/user/login',{
                email:this.signinForm.sEmail,
                password:this.signinForm.sPassword
            }).then((res)=>{
                //console.log(res)
                if(res.data.status==0){
                    console.log(res.data.data.username)
                    //console.log(res.data.data.uid)
                    this.UID=res.data.data.uid
                    this.$cookie.set('userId',res.data.data.uid,{expires: '1M'});
                    this.$cookie.set('userName',res.data.data.username,{expires: '1M'});
                    this.$store.dispatch('saveUserName',res.data.data.username)
                    this.save()
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
        getTime(){
            var date=new Date();
            var year=date.getFullYear();
            var month=date.getMonth()+1;
            var day=date.getDate();
            var hours=date.getHours();
            var minutes=date.getMinutes();
            var seconds=date.getSeconds();
            if(month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if(day >= 0 && day <= 9){
                day = "0" + day;
            }
			if(hours >= 0 && hours <= 9){
                hours = "0" + hours;
            }
			if(minutes >= 0 && minutes <= 9){
                minutes = "0" + minutes;
            }
			if(seconds >= 0 && seconds <= 9){
                seconds = "0" + seconds;
            } 
            this.currentDate=year+"-"+month+"-"+day//+" "+hours+":"+minutes+":"+seconds
        }

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
