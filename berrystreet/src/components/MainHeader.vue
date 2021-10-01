<template>
      <div class="main-header">
        <img src="./../assets/lexm.png" alt="">
        <span class="header-content">{{title}}</span>
        <el-button @click="logout()" style="float:right;margin-right:25px;margin-top:25px;">Logout</el-button>
      </div>
</template>

<script>
export default {
    name:'main-header',
    data(){
        return{

        }
    },
    props:{
        title:String
    },
    methods:{
        logout(){
            this.$message({
                showClose: true,
                message: 'You have logged out',
                type: 'success'
            });
            this.$cookie.set('userId','',{expires: '-1'})
            this.$cookie.set('userName','',{expires: '-1'})
            this.$store.dispatch('saveUserName','')
            this.$router.push('/')
        },
        
    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .main-header{
        height: 90px;
        line-height: 90px;
        background-color: #50A7C2;
        text-align: center;
        position: relative;
        
        img{
            width: 190px;
            height: 40px;
            position: absolute;
            top: 0;
            bottom: 0;
            left: 10px;
            margin: auto;
        }
        .header-content{
            font-size: 45px;
            font-family: 'Acme', sans-serif;  
            color: #F8F8FF;
        }
        
    }
</style>