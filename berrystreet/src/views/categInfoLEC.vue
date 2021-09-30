<template>
    <div class="categ">
        <nonText-header></nonText-header>
        <el-button @click="goBack" style = "float:left;margin-left:15px;margin-top:15px;background-color:lightblue;font-size:25px"> Back </el-button>

        <div class="main"> 
            <div class = "main-back"> 
                <h2 class = "title"> {{topic}} </h2><br>
                <p class = "overview"> Overview </p>
            </div>
            <div class="main-content">
                <p class = "content"> {{overview}} </p>
            </div>
        </div>
        
        <div class="start-quiz">
            <el-button @click="startQuiz" style = "background-color:lightblue;font-size:35px"> Start the quiz </el-button>
        </div>
        
        <div class="footer">
        
        </div>
    </div>
</template>



<script>
import NonTextHeader from './../components/NonTextHeader.vue'
export default {
    data(){
        return{
            quizid:'',
            topic:'',
            overview:''
        }
    },
    name:'categInfoLEC',
    components:{

        NonTextHeader
    },
    methods:{
        goBack(){
            this.$router.push('/categPanel')
        },
        startQuiz(){
            this.$router.push({
                name: 'selectQuestion',
                params: {
                    id: this.quizid
                }
            })
        },
        loadQuizOverview(){
            let qid = this.quizid;
            this.axios.get(`/api/quiz/getQuiz/${qid}`).then((res)=>{
                this.topic =res.data.data.topic;
                this.overview = res.data.data.overview;
            })
        }
    },
    mounted(){
        this.quizid = this.$route.params.id;
        this.loadQuizOverview();
    }
    
}
</script>



<style lang="scss">
.categ{
    //background-color: #465458;

    .main{
        
        .main-back{
            width: 90%;
            height:50%;
            margin: 80px auto;
            border-radius: 15px;
            text-align: center;
            background-color: #50A7C2;
            .title{
                font-family: 'Acme', sans-serif;
                font-size: 50px;
                text-align: center;
                margin-right: 50px;
                display: inline-block;
                margin-top: 25px;
            }
            .overview{
                font-family: 'Acme', sans-serif;
                font-size: 35px;
                // text-align: center;
                display: inline-block;
            }
        }
        .main-content{
            width: 90%;
            height:50%;
            margin: 40px auto;
            border-radius: 15px;
            font-family: 'Acme', sans-serif;  
            background-color: lightblue;
            .content{
                padding: 0 30px 0 0;
                font-family:'Acme', sans-serif ;
                font-size: 30px;
                margin-top: 3%;
                margin-left: 3%;
                height: 350px;
            }
        }
    }
    .start-quiz{
        font-family: 'Acme', sans-serif;  
        text-align: center;
        font-size: 35px;
        
    }
    
}
</style>