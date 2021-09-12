<template>
<div class="question">
    <nonText-header></nonText-header>
    <el-button @click="exit" style = "float:left;margin-left:25px;margin-top:25px;background-color:lightblue;font-size:35px"> Exit </el-button>
    <div class="question-main">
        <h2 class="question-title" >{{this.currentQuestion.qContent}}</h2>
        <br />
        <!-- <el-col> -->
          <el-row
            :span="4"
            v-for="item in this.currentQuestion.options"
            :key="item.content"
            :offset="1"
          >
            <div class="choice">
                <el-button @click="nextQuestion(item)" style = "background-color:lightblue;font-size:35px">
                    {{item.content}}
                </el-button>
            </div>
            <br />
          </el-row>
    
    </div>
  </div>
</template>


<script>
import NonTextHeader from './../components/NonTextHeader.vue'
export default {
    name:'selectQuestion',
    components:{
        NonTextHeader
    },
    data(){
        return{
            data:{
                "stauts": 0,
                "msg": "success",
                "data": {
                    "QID": "1",
                    "topic": "lol",
                    "overview": "game",
                    "questions":[
                        {"qID":1,"quizId":101,"qContent":"When the group needs suggestions, I...",
                        "options":[{"key":0, "content":"Do not make suggestions","point":1},{"key":1,"content":"Tell the group what to do","point":2},
                                {"key":2, "content":"Discuss my suggestions with the group","point":3},{"key":3, "content":"Make sure everyone's suggestions are heard","point":5}]},
                        {"qID":2,"quizId":101,"qContent":"When the group needs opinions about something, I...",
                        "options":[{"key":0, "content":"Do not give my opinion","point":1},{"key":1,"content":"Give my opinion","point":2},
                                {"key":2, "content":"Explain my opinion so the group understands","point":4},{"key":3,"content":"Give reasons for and against my opinion using evidence","point":6}]}
                    ]
                }
            },
            questions: [],
            currentQuestion: {},
            index: 0,
            scores: 0
        }
    },
    created() {
        this.fetchQuestions()
        
    },
    methods:{
        exit(){
            alert("exit");
            console.log(1111)
        },
        fetchQuestions() {
            this.axios.post('/api/quiz/takeQuiz?QID=',{
                    quizID:this.QID
                }).then((res) => {
                console.log("res = ", res);
                let data = res.data;
                this.questions = data.questions;
            });
            // this.axios.get("/api/quiz/takeQuiz?QID=1").then((res) => {
            //     console.log("res = ", res);
            //     let data = res.data;
            //     this.questions = data.questions;
            // });
            // this.setQuestions();
        },
        setQuestions() {
            this.index=0
            this.questions=[
                {"qID":1,"quizId":101,"qContent":"When the group needs suggestions, I...",
                        "options":[{"key":0, "content":"Do not make suggestions","point":1},{"key":1,"content":"Tell the group what to do","point":2},
                                {"key":2, "content":"Discuss my suggestions with the group","point":3},{"key":3, "content":"Make sure everyone's suggestions are heard","point":5}]},
                {"qID":2,"quizId":101,"qContent":"When the group needs opinions about something, I...",
                        "options":[{"key":0, "content":"Do not give my opinion","point":1},{"key":1,"content":"Give my opinion","point":2},
                                {"key":2, "content":"Explain my opinion so the group understands","point":4},{"key":3,"content":"Give reasons for and against my opinion using evidence","point":6}]}
                ]
            this.$root.$data.state=this.questions;
            // console.log(this.questions[0].qContent)
            this.currentQuestion=this.questions[this.index];
            // console.log(this.index)
            // console.log(this.currentQuestion.qContent)
        },
        nextQuestion(item){
            console.log(item.content)
            this.scores+=item.point
            console.log(this.scores)
            this.index+=1
            if (this.questions.length > this.index) {
                this.currentQuestion = this.questions[this.index]
            } else {
                // this.axios.post("/api/feedback?score="+this.scores).then((res) => {
                //     console.log("res = ", res);
                // });

                this.$router.replace({ name: 'result' })
            }
        }
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
}
</style>