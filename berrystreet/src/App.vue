<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>


export default {
  name: 'App',
  components: {
    
  },
  methods:{
    getid(){
      this.$store.dispatch('saveUserName',this.$cookie.get('userName'))
    }
  },
  mounted(){
    this.getid()
  }
}
</script>

<style lang="scss">
  @import './assets/scss/reset.scss';
</style>
