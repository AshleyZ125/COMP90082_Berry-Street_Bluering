<template>
    <div class="categ">
        <nonText-header></nonText-header>
        <el-button @click="goBack" style = "float:left;margin-left:25px;margin-top:25px;background-color:lightblue;font-size:35px"> Back </el-button>

        <div class="main"> 
            <div class = "main-back"> 
                <h2 class = "title"> Communication </h2>
                <p class = "overview"> Overview </p>
            </div>
            <div class="main-content">
                <p class = "content"> Lorem ipsum dolor sit amet consectetur adipisicing elit. Repudiandae doloremque similique explicabo, dolorem officiis, delectus, vitae fuga voluptas totam molestiae temporibus odit dignissimos quo amet neque! Reprehenderit quibusdam dicta ab tempora ex optio repellendus illo numquam provident, ipsa placeat asperiores nulla et illum at impedit accusantium maiores magni laudantium fuga. Et, minus quibusdam asperiores consequatur voluptatum deserunt vero, distinctio at inventore sunt ex, eius cumque eveniet molestiae atque quam eum dignissimos ullam ad cupiditate quos eos quisquam ducimus? Vero commodi esse aliquam odio labore blanditiis, animi tempore suscipit tenetur eligendi doloremque optio, debitis quo veritatis atque ab minima mollitia? Quas. </p>
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
    name:'categInfoLEC',
    components:{

        NonTextHeader
    },
    methods:{
        goBack(){
            alert("back");
            console.log(1111)
        },
        startQuiz(){
            alert("start quiz");
            console.log(222)
        }
    }
    
}
</script>



<style lang="scss">
.categ{
    //background-color: #465458;

    .main{
        
        .main-back{
            width: 2000px;
            height:200px;
            margin: 150px auto;
            border-radius: 15px;
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
            width: 2000px;
            height:600px;
            margin: 40px auto;
            border-radius: 15px;
            font-family: 'Acme', sans-serif;  
            background-color: lightblue;
            .content{
                padding: 0 30px 0 0;
                font-family:'Acme', sans-serif ;
                font-size: 35px;
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