<template>
  <div class="createQuizTitle">
      <main-header title="Edit Quiz"></main-header>
      <div class="pageBody">
          <div class="pageContainer">
              <el-form :model="quizForm" ref="quizForm"  label-width="100px" label-position="top" :rules="quizFormRules" style="margin-left:30%;margin-top:10%">
                    <el-form-item label="Title" prop="title" style="font-size:30px">
                        <el-input style="width:300px" v-model="quizForm.title" placeholder="Please input quiz title"></el-input>
                    </el-form-item>
                    <el-form-item label="Quiz Overview" prop="quizoverview">
                        <el-input type="textarea" v-model="quizForm.quizoverview" placeholder="Please input quiz overview" :rows="12" style="width:600px"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="cancelEdit" style="margin:20px 0 0 100px">Cancel</el-button>
                        <el-button @click="submitForm('quizForm')" type="primary" style="margin:20px 0 0 100px">Continue</el-button>
                    </el-form-item>
                </el-form>
          </div>
      </div>
  </div>
</template>

<script>
import MainHeader from './../components/MainHeader'
export default {
    name:'editquiztitle',
    data(){
        return{
            QID:'',
            userId:'',
            quizForm:{
                title:'',
                quizoverview:'',
            },
            quizFormRules:{
                title: [
                    { required: true, message: 'Please input quiz title', trigger: 'blur' },
                    { min: 1, max: 50, message: 'The length should between 1 to 50 words', trigger: ['blur','change'] },
                ],
                quizoverview:[
                    { required: true, message: 'Please input quiz overview', trigger: 'blur' },
                    { min: 5, max: 300, message: 'The length should between 5 to 300 words', trigger: ['blur','change'] },
                ]
            }
            
        }
    },
    components:{
        MainHeader
    },
    mounted(){
        this.QID=this.$route.params.id
        this.userId = this.$cookie.get('userId')
        this.getQuizInfo();
    },
    methods:{
        getQuizInfo(){
            let quizid = this.QID;
            this.axios.get(`/api/quiz/getQuiz/${quizid}`).then((res)=>{
                console.log(res.data.data)
                this.quizForm.title=res.data.data.topic;
                this.quizForm.quizoverview= res.data.data.overview;
            })
        },
        cancelEdit(){
            this.$confirm('You have not finished yet. Are you sure you wanna cancel it?', 'Tips', {
                type: 'warning',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.$router.push('/myquiz')
            })
        },
        editQuiz(){
            this.axios.post('/api/quiz/setQuiz',{
                //creatorID:this.userId,
                 topic:this.quizForm.title,
                 overview:this.quizForm.quizoverview,
                 QID:this.QID
             }).then((res)=>{
                 let QID=res.data.data;
                 if(res.status==200){
                    //  this.$message({
                    //     message: 'Quiz information has been updated, Please continue!',
                    //     showClose: true,
                    //     type: 'success', 
                    // }) 
                    this.$router.push({
                        path: '/createquestion/'+this.QID,
                    });
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
                this.editQuiz();
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
.createQuizTitle{
    font-family: 'Acme', sans-serif; 
    .pageBody{
            width: 100%;
            .pageContainer{
                width: 1300px;
                margin: 30px auto;
            }

        }
        .el-form-item__label{
            font-size: 25px;
            font-family: 'Acme', sans-serif;  
            color: #606266;
        }
}
</style>