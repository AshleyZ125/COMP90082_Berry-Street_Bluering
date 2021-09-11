<template>
  <div class="categPanel">
    <NonTextHeader></NonTextHeader>
    <div class="panelBody">
      <h1 class="title">Quizzes</h1>
      <div
        class="cards"
        style="font-family: 'Acme', sans-serif; font-size: 50px"
      >
        <el-row>
          <el-col
            :span="4"
            v-for="item in tabledata"
            :key="item.QID"
            :offset="1"
          >
            <div @click="gotoCateg(item)" style="margin-top: 30px">
              <el-card
                shadow="hover"
                :body-style="{
                  background: 'pink',
                  borderRadius: 10,
                }"
              >
                <span>{{ item.topic }}</span>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
    <div class="bottom">
        <div class="desc">
           <p style = "font-family: 'Acme', sans-serif;font-size:15px;">
              How do you feel about using LEx Mirror? Tell us how you think we can improve 
            </p>
        </div>
        <div class="btn">
          <el-button @click="gotoComment()"> Feedback </el-button>
            
        </div>
    </div>
    </div>
  </div>
</template>

<script>
import NonTextHeader from "./../components/NonTextHeader.vue";

export default {
  data() {
    return {
      tabledata: [
        // { topic: "study2", overview: "234", status: 3, creatorID: 2, QID: 2 },
        // {
        //   topic: "topic1",
        //   overview: "overview1",
        //   status: 3,
        //   creatorID: 1,
        //   QID: 4,
        // },
      ],
    };
  },
  name: "categPanel",
  components: {
    NonTextHeader,
  },
 created() {
    this.allAjax();
 },
  methods: {
    gotoCateg(item) {
       this.$router.push({
          path: `/particulars/${item.QID}`,
        })
    },
    allAjax() {
      this.axios.post("/api/quiz/lecQuizList").then((res) => {
        console.log("res = ", res);
        let data = res.data;
        this.tabledata = data;
      });
    },
    gotoComment(){
      alert("go to comment")
    }
  },
};
</script>


<style lang="scss">
//@import "./categPanel.scss";
.title{
  font-family: 'Acme', sans-serif;  
  font-size:30px;
  margin-left:30px;
  margin-top:10px;
}
.el-card, .el-message{
    border-radius: 10px !important;
}
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
