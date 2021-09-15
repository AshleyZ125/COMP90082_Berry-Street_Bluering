<template>
<div class="question">
    <nonText-header></nonText-header>
    <el-button @click="exit" style = "float:left;margin-left:25px;margin-top:25px;background-color:lightblue;font-size:35px"> Exit </el-button>
    <div class="question-main">
        <h2 class="question-title" >{{this.currentQuestion.qcontent}}</h2>
        <br />
        <!-- <el-col> -->
          <el-row
            :span="4"
            v-for="item in this.currentQuestion.options"
            :key="item.key"
            :offset="1"
          >
            <div class="choice">
                <el-button @click="nextQuestion(item)" style = "background-color:lightblue;font-size:35px">
                    {{item.value}}
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
            quizid:'',
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
            scores: 0
        }
    },
    mounted(){
        this.quizid = this.$route.params.id;
        this.fetchQuestions();
    },
    methods:{
        exit(){
            alert("exit");
            console.log(1111)
        },
        fetchQuestions() {
            let quizid = this.quizid;
            // this.axios.get(`/api/quiz/getQuiz/${qid}`).then((res)=>{
            //     this.topic =res.data.data.topic;
            //     this.overview = res.data.data.overview;
            // })
            this.axios.get(`/api/quiz/takeQuiz?QID=${quizid}`).then((res) => {
                //console.log(res.data.data);
                let data = res.data;
                this.questions = res.data.data.questionList;
                console.log(this.questions);
                this.setQuestions();
            });
            
            
        },
        setQuestions() {
            this.index=0
            // this.questions=[
            //     {"qID":1,"quizId":101,"qContent":"When the group needs suggestions, I...",
            //             "options":[{"key":0, "content":"Do not make suggestions","point":1},{"key":1,"content":"Tell the group what to do","point":2},
            //                     {"key":2, "content":"Discuss my suggestions with the group","point":3},{"key":3, "content":"Make sure everyone's suggestions are heard","point":5}]},
            //     {"qID":2,"quizId":101,"qContent":"When the group needs opinions about something, I...",
            //             "options":[{"key":0, "content":"Do not give my opinion","point":1},{"key":1,"content":"Give my opinion","point":2},
            //                     {"key":2, "content":"Explain my opinion so the group understands","point":4},{"key":3,"content":"Give reasons for and against my opinion using evidence","point":6}]}
            //     ]
            this.$root.$data.state=this.questions;
            // console.log(this.questions[0].qContent)
            this.currentQuestion=this.questions[0];
            // console.log(this.index)
            // console.log(this.currentQuestion.qContent)
            
            console.log(this.currentQuestion)
        },
        nextQuestion(item){
            console.log(item.value)
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