<template>
    <div class = "myQuizzesHeader"> 
        <img src = "./../scss/lexm.png" alt = "icon picture">
        <span class = "myQuizzesText"> My Quizzes </span>
        <el-button @click="logIn()" style = "float:right;margin-right:25px;margin-top:25px;"> Log in </el-button>
    </div>
</template>

<script>
export default{
    name:'myQuizzes-header',
    methods:{
        logIn(){
            alert("logIn");
        }
    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
   .myQuizzesHeader{
        height: 90px;
        line-height: 90px;
        background-color: #50A7C2;
        text-align: center;
        position: relative;

        .img{
            width: 190px;
            height: 40px;
            position: absolute;
            top: 0;
            bottom: 0;
            left: 10px;
            margin: auto;
        }
        .myQuizzesText{
            font-size: 45px;
            font-family: 'Acme', sans-serif;  
            color: #F8F8FF;
        }
   }

</style>