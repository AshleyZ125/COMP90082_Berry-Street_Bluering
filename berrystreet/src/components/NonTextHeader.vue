<template>
  <div class="lecHeader">
    <img src="./../assets/lexm.png" alt="" @click="gotoHome">
    
    <div  class= "button">
        <!-- <el-button 
            @click="logIn()" 
            style = "float:right;margin-right:25px;margin-top:15px;font-size:35px"
            disabled = false
            :visible.sync = "loginVisible"> Log In </el-button> -->

        <el-button 
            v-if = "isShow"
            @click="logIn()" 
            style = "float:right;margin-right:25px;margin-top:15px;font-size:35px"
            > Log In </el-button>




    </div>
  </div>
</template>

<script>
export default {
    name:'nonText-header',
    data(){
        return{
            isShow: true,

            // loginVisible: true,
            // logoutVisible: false,
            // userId: -1,
        }
    },

    mounted() {
        this.userId = this.$route.params.userId
        this.showOrNot()
    },

    methods:{
        logIn(){
            this.$router.push('/signinLEC')
        },

        logOut(){
            alert("log out")
        },
        gotoHome(){
            this.$router.push('/')
        },
        showOrNot(){
            if (this.userId == null || this.userId == -1){
                console.log("userId是", this.userId)
                this.isShow = true
            } else {
                console.log("userId是", this.userId)
                this.isShow = false
            }
        }


        // try----------------------------------------------------------------------------------------
        
        // try----------------------------------------------------------------------------------------


    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .lecHeader{
        height: 90px;
        line-height: 90px;
        background-color: #50A7C2;
        //text-align: center;
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
        
        .footer{
            text-align: center;
        }
    }
</style>