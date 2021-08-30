<template>
  <div>
      <nav-header></nav-header>
      <div class="container">
          <div class="card" id="lec" @click="gotoQuiz()">
                <span>I'm a person with lived experience</span>
                <img src="./../assets/student.png" alt="">
          </div>
          <div class="card" id="sup" @click="gotoSignin()">
              <span>I'm a person who supervises people with lived experience</span>
              <img src="./../assets/teacher.png" alt="" id="teacher">
          </div>
      </div>
  </div>
</template>

<script>
import NavHeader from './../components/NavHeader.vue'
export default {
    name:'home',
    components:{
        NavHeader
    },
    methods:{
        gotoSignin(){
            this.$router.push('/signin')
        },
        gotoQuiz(){
            alert("22")
        }
    }
}
</script>

<style lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');

.container{
    display: flex;
    padding: 10% 0;
    .card{
        width: 350px;
        height: 300px;
        border-radius: 15px;
        margin: 0 auto;
        text-align: center;
        padding: 5% 1%;
        color: white;
        position: relative;
        cursor: pointer;
        span{
            font-size: 40px;
            font-family: 'Acme', sans-serif;  
        }
        img{
            width: 150px;
            height: 150px;
            position: absolute;
            bottom: 10px;
            right: 0;
            left: 0;
            margin: auto;
        }
        #teacher{
            width: 120px;
            height: 120px;
            bottom: 25px;
        }
    }
    #lec{
        margin-right: 50px;
        background-image: linear-gradient(60deg, #B7F8DB 0%, #50A7C2 100%);
    }
    #sup{
        margin-left: 50px;
        background-image: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
    }
    
}
</style>