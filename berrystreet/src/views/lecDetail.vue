<template>
  <div class="lecdetail">
      <main-header :title="username+'‘s  '+topic+'  Feedback'"></main-header>
      <div class="pageBody">
          <div class="pageContainer">
            <el-table :data="quiz" style="width: 100%" empty-text="No data"  :header-cell-style="{background:'#eee',color:'#606266',fontSize: '20px'}">
                <el-table-column label="Question" align="center">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <span style="font-size:24px">{{ scope.row.content }}</span>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="selected option" align="center">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <span style="font-size:24px">{{ scope.row.option }}</span>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
            <h2>Feedback</h2>
            <el-input type="textarea" :rows="4" v-model="feedback" readonly></el-input>
            <h2>Reflection Diary</h2>
            <el-input type="textarea" :rows="4" placeholder="This LEC does not write any reflections" v-model="diary" readonly></el-input>
            <div style="text-align:center;margin-top:30px">
                 <el-button type="primary" @click="goBack">Back</el-button>
            </div>
           
          </div>
      </div>
  </div>
</template>

<script>
import MainHeader from './../components/MainHeader.vue'
export default {
    name:'lecdetail',
    data(){
        
        return{
            userId:'',
            quiz:[],
            feedback:'',
            diary:'',
            username:'',
            topic:'cooking learning',
            title:''
        }
    },
    components:{
        MainHeader,
    },
    mounted(){
        this.SID=this.$route.params.id
        this.username = this.$route.params.name
        this.getDetail();
    },
    methods:{
        getDetail(){
            let shareId = this.SID;
                this.axios.get(`/api/record/getSharedFeedback/${shareId}`).then((res)=>{
                    console.log(res.data)
                    this.quiz = res.data.data.quizContent
                    this.feedback = res.data.data.feedback
                    this.diary = res.data.data.savedReflection
                })
        },
        goBack(){
            this.$router.push('/mylec')
        }

      
    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .lecdetail{
        font-family: 'Acme', sans-serif;  
        h2{
            color: #606266;
        }
        .pageBody{
            width: 100%;
            .pageContainer{
                width: 1300px;
                margin: 30px auto;
                h2{
                    font-size: 40px;
                    margin-bottom: 10px;
                }
            }

        }
        
    }
</style>