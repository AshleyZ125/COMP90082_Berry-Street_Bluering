<template>
  <div class="header">
      <img src="./../assets/lexm.png" alt="">
      <span>Welcome to LEx Mirror!</span>
  </div>
</template>

<script>
export default {
    name:'nav-header'

}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .header{
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
        span{
            font-size: 45px;
            font-family: 'Acme', sans-serif;  
            color: #F8F8FF;
        }
    }
</style>