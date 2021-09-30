<template>
  <div class="categPanel">
    <NonTextHeader></NonTextHeader>
    <div class="panelBody">
      
      <h1 class="title">Quizzes</h1>
      <div class="cards" style="font-family: 'Acme', sans-serif; font-size: 50px">
        <el-row>
          <el-col   :span="4"   v-for="item in tabledata"    :key="item.qid"     :offset="1">
            <div @click="gotoCateg(item)" style="margin-top: 30px">
              <el-card
                shadow="hover"
                :body-style="{
                  background: 'lightblue',
                  borderRadius: 10,
                }"
              >
                <span>{{ item.topic }}</span>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
    
    <FeedbackFooter></FeedbackFooter>
    </div>
  </div>
</template>

<script>
import NonTextHeader from "./../components/NonTextHeader.vue";

import FeedbackFooter from "./../components/FeedbackFooter.vue";

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
    FeedbackFooter,
  },
 mounted() {
    this.allAjax();
 },
  methods: {
    gotoCateg(item) {
      //console.log(item.qid)
        this.$router.push({
                name: 'categInfoLEC',
                params: {
                    id: item.qid
                }
            })
    },
    allAjax() {
      this.axios.post("/api/quiz/lecQuizList").then((res) => {
        console.log("res = ", res);
        let data = res.data.data;
        this.tabledata = data;
      });
    },
    
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
.el-card{
  width:400px;
}
.el-card, .el-message{
    border-radius: 10px !important;
}

</style>
