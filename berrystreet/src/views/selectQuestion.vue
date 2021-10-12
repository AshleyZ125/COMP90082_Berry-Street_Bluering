<template>
<div class="question">
    <quiz-header></quiz-header>
    <el-button @click="exit" style = "float:left;margin-left:25px;margin-top:25px;background-color:lightblue;font-size:35px"> Exit quiz</el-button>
    <div class="question-main">
        <h2 class="question-title" >{{this.currentQuestion.qcontent}}</h2>
        <br />
          <el-row
            :span="4"
            v-for="item in this.currentQuestion.options"
            :key="item.key"
            :offset="1"
          >
            <div class="choice">
                <el-button :class="{'clickBg':item==clickItem}" @click="clickChoice(item)" style = "background-color:lightgrey;font-size:35px">
                    {{item.value}}
                </el-button>
            </div>
            <br />
          </el-row>
        
    </div>
    <el-button id="pre" @click="preQuestion" style = "display:none;float:left;margin-left:25px;margin-bottom:25px;background-color:lightblue;font-size:35px"> previous </el-button>
    <el-button id="next" @click="nextQuestion" style = "float:right;margin-right:25px;margin-bottom:25px;background-color:lightblue;font-size:35px"> next </el-button>
    <feedback-footer></feedback-footer>
  </div>
</template>


<script>
import QuizHeader from './../components/QuizHeader.vue'
import FeedbackFooter from './../components/FeedbackFooter.vue'
export default {
    name:'selectQuestion',
    components:{
        FeedbackFooter,
        QuizHeader
    },
    data(){
        return{
            UID: -1,
            quizId:'',
            quizTopic: "",
            data:{
                // "stauts": 0,
                // "msg": "success",
                // "data": {
                //     "QID": "1",
                //     "topic": "lol",
                //     "overview": "game",
                //     "questions":[
                //         {"qID":1,"quizId":101,"qContent":"When the group needs suggestions, I...",
                //         "options":[{"key":0, "content":"Do not make suggestions","point":1},{"key":1,"content":"Tell the group what to do","point":2},
                //                 {"key":2, "content":"Discuss my suggestions with the group","point":3},{"key":3, "content":"Make sure everyone's suggestions are heard","point":5}]},
                //         {"qID":2,"quizId":101,"qContent":"When the group needs opinions about something, I...",
                //         "options":[{"key":0, "content":"Do not give my opinion","point":1},{"key":1,"content":"Give my opinion","point":2},
                //                 {"key":2, "content":"Explain my opinion so the group understands","point":4},{"key":3,"content":"Give reasons for and against my opinion using evidence","point":6}]}
                //     ]
                // }
            },
            questions: [],
            currentQuestion: {},
            index: 0,
            scores: 0,
            // choice: null,
            record:[],
            clickItem: -1,
            feedbacks:[],
            feedback:""
        }
    },
    mounted(){
        this.UID=this.$route.params.userId;
        this.quizId = this.$route.params.id;
        this.quizTopic = this.$route.params.topic;
        this.fetchQuestions();
    },
    methods:{
        exit(){
            if(this.UID!=null&&this.UID!=-1){
                this.$router.push({
                    name: 'myspaceLEC',
                    params: {
                        userId: this.UID,
                    }
                })
                
            }else{
                this.$router.push({
                    name: 'categPanel',
                    params: {
                        userId: this.UID,
                    }
                })
            }
        },
        fetchQuestions() {
            console.log("quizid:"+this.quizId)
            this.axios.get(`/api/quiz/takeQuiz?QID=${this.quizId}`).then((res) => {
                console.log(res.data.data);
                // let data = res.data;
                this.questions = res.data.data.questionList;
                console.log(this.questions);
                this.setQuestions();
            });
            // this.setQuestions();
            
        },
        setQuestions() {
            this.index=0
            // this.questions=[
            //     {"qID":1,"quizId":101,"qcontent":"When the group needs suggestions, I...",
            //             "options":[{"key":0, "value":"Do not make suggestions","point":1},{"key":1,"value":"Tell the group what to do","point":2},
            //                     {"key":2, "value":"Discuss my suggestions with the group","point":3},{"key":3, "value":"Make sure everyone's suggestions are heard","point":5}]},
            //     {"qID":2,"quizId":101,"qcontent":"When the group needs opinions about something, I...",
            //             "options":[{"key":0, "value":"Do not give my opinion","point":1},{"key":1,"value":"Give my opinion","point":2},
            //                     {"key":2, "value":"Explain my opinion so the group understands","point":4},{"key":3,"value":"Give reasons for and against my opinion using evidence","point":6}]},
            //     {"qID":3,"quizId":101,"qcontent":"33333When the group needs opinions about something, I...",
            //             "options":[{"key":0, "value":"Do not give my opinion","point":1},{"key":1,"value":"Give my opinion","point":2},
            //                     {"key":2, "value":"Explain my opinion so the group understands","point":4},{"key":3,"value":"Give reasons for and against my opinion using evidence","point":6}]}
            //     ]
            this.currentQuestion=this.questions[0];
            this.record=new Array(this.questions.length);
            console.log(this.currentQuestion)
        },
        clickChoice(item){
            // this.choice=item
            this.record[this.index]=item;
            this.clickItem=item
        },
        preQuestion(){
            this.index-=1
            if(this.index==0){
                var preBtn = document.getElementById("pre");
                preBtn.style.display="none"
            }
            this.currentQuestion = this.questions[this.index]
            this.clickChoice(this.record[this.index])
            var nextBtn = document.getElementById("next");
            nextBtn.innerHTML="next";
            nextBtn.style.color="default"
        },
        nextQuestion(){
            if(this.record[this.index]==null){
                this.$message({
                    showClose: true,
                    message: 'Please choose one option!',
                    type: 'error'
                });
            }else{
                var preBtn = document.getElementById("pre");
                preBtn.style.display="block";
                // console.log(this.choice.value);
                this.index+=1
                if (this.questions.length > this.index) {
                    this.currentQuestion = this.questions[this.index]
                    this.clickChoice(this.record[this.index])
                    if(this.questions.length == this.index+1){
                        var nextBtn = document.getElementById("next");
                        nextBtn.innerHTML="finish";
                    }
                } else {
                    var result=new Array(this.questions.length);
                    
                    for(var i=0; i<this.record.length; i++){
                        this.scores+=this.record[i].point;
                        result[i]={"content":this.questions[i].qcontent, "option":this.record[i].value}
                    }
                    console.log(this.scores)
                    console.log(result)

                
                    console.log("id: "+this.quizid)
                    this.$router.push({
                        name: 'getFeedback',
                        params: {
                            userId: this.UID,
                            quizId: this.quizId,
                            scores: this.scores,
                            result: result,
                            topic: this.quizTopic
                            }
                    })
                      
                }
            }
        },
    }
    
}
</script>
<style lang="scss">
.question{
    .question-title{
        font-family: 'Acme', sans-serif;
        font-size: 50px;
        text-align: center;
        // margin-right: 100px;
        // display: inline-block;
        margin-top: 150px;
    }

    .choice{
        font-family: 'Acme', sans-serif;  
        text-align: center;
        font-size: 35px;
    }
    .clickBg{
        // background: red;
        color: #409EFF;//rgb(93, 153, 231);
    }
}
</style>
