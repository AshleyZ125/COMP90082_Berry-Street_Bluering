<template>
    <div class = "bottom">
        <div class="desc">
            <p style = "font-family: 'Acme', sans-serif;font-size:15px;">
                 How do you feel about using LEx Mirror? Tell us how you think we can improve 
            </p>
        </div>
        <div class="btn">
            <el-button @click="gotoComment()"> Feedback </el-button>
        </div>

    </div>
</template>

<script>
export default {
    name:'feedback-footer',
    methods:{
        gotoComment(){
            // this.$router.push('')
            alert("go to Comment")
        }
    }
}
</script>

<style lang="scss">
.bottom{
    position: fixed;
    bottom: 0;
    left: 0;
    height: 50px;
    width: 100%;
    background: #50A7C2;
    display: flex;
    color: white;
    justify-content: center;
    align-items: center;
    .btn{
        margin-left: 20px;
        margin-right: 30px;
        //width: 200px;
        // height: 0px;
        text-align: center;
        // line-height: 50px;
        color: black;
        padding: 5px 0;
        //background: white;
        //box-shadow: 0 0 20px rgba(255,255,255,0.3);
        //justify-content: end;
    }
}

</style>
