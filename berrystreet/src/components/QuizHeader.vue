<template>
  <div class="lecHeader">
    <img src="./../assets/lexm.png" alt="">
  </div>
</template>

<script>
export default {
    name:'quiz-header',
    methods:{
        
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
        
    }
</style>