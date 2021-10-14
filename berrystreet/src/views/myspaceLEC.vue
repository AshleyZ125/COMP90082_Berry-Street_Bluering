<template>
    <div class = "myspaceLEC-main">
        <SpaceHeader></SpaceHeader>
        <div class="drop-div">
          <el-dropdown style="cursor: pointer;float:right;margin-right:25px;margin-top:20px;font-weight:bold;font-size:20px">
            <span class="el-dropdown-link">
                Settings<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><span @click="gotoReset()">Reset Password</span></el-dropdown-item>
                <el-dropdown-item><span @click="gotoResetEmail()">Reset Email</span></el-dropdown-item>
                <el-dropdown-item><span @click="gotoSettings()">Account Settings</span></el-dropdown-item>
                <!-- <el-dropdown-item><a href="/">Account Settings</a></el-dropdown-item> -->
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class = "takeQuiz">
            <el-button @click="takeQuiz()" >Take a Quiz</el-button>
        </div>
        <div class ="space-container">
            
            <!-- <div class = "table"> -->
                <div class="pageBody">
                    <div class="pageContainer">
                <el-table 
                    :data="tableData"
                    style="width: 100%"
                    empty-text="You don't have records yet!"> <!-- :span= "1" v-for="item in tableData"    :key="item.quizTopic " :offset="1"  -->
                    
                    
                    <el-table-column  label="Quiz Topic" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:15px">{{ scope.row.topic }}</span>
                            </div>
                        </template>
                    </el-table-column>

                    <el-table-column  label="Date" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:15px">{{ scope.row.date }}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column  label="View" align="center">
                        <template slot-scope="scope">
                            <el-button  type="primary" @click="gotoView(scope.$index, scope.row)">View</el-button>
                            
                        </template>
                        <!-- <el-button @click="gotoView(RID)" type="text" > View </el-button> -->
                        
                    </el-table-column>
                </el-table>
                </div>
                </div>
            <!-- </div> -->
            <!-- <div class = "takeQuiz">
                <el-button @click="startQuiz" style = "background-color:lightblue;font-size:35px"> Start the quiz </el-button>
            </div> -->
        </div>
        
        <!-- <div class = "takeQuiz">
            <el-button @click="takeQuiz()" >Start Quiz</el-button>
        </div> -->

        <Footer></Footer>
    </div>
</template>

<script>

import SpaceHeader from "./../components/SpaceHeader.vue"
import Footer from "./../components/Footer.vue"

export default {
    name: "myspaceLEC",
    data(){
        return{
            userId:'',
            tableData:[
            //     {
            //     RID:1,
            //     savedReflection:"234",
            //     feedBack:"234",
            //     topic:"234",
            //     userID:2,
            //     rDate:"",
            //     quizContent:[{"qContent":"234","option":"234"}]
            //     // quizTopic: 'Collaborative learning',
            //     // qid:'1',
            //     // date: '2021-01-01',
            //     // view: 'view'
            //  }],
            // }
             ]
         };
    },
    components:{
        SpaceHeader,
        Footer,
    },

    mounted() {
        this.userId = this.$cookie.get('userId')
        this.allAjax();
    },

    methods:{
        gotoReset(){
            this.$router.push('/resetPassword')
        },

        gotoResetEmail(){
            this.$router.push('/resetEmail')
        },
        gotoSettings(){
            this.$router.push('/personalSetting')
        },

        takeQuiz(){
            this.$router.push({
                name:'categPanel',
                params: {
                    userId: this.userId
                }
            })
        },

        gotoView(index, row){
            // alert("go to see pastquiz and feedback and diary")                        // add 跳转页面
            console.log("row = ", row),
            this.$router.push({
                
                name: 'pastFeedbackLEC',
                params: {
                    RID: row.rid,
                    userId: this.userId,
                }
            })
        },

        // startQuiz(){
        //     this.$router.push('/categPanel')
        // },

        allAjax() {
            console.log(this.userId)
            this.axios.get(`/api/record/getRecordList/${this.userId}`).then((res) => {  //API need insert
            console.log("res = ", res);
            let data = res.data.data;                                                                            // check the path
            this.tableData = data;
           
            });
        },
    }
}
    


</script>





<style lang="scss">
    .myspaceLEC-main{

        .takeQuiz{
        // position: relative;
        // margin:20px 0 0 100px;
        margin-bottom: 20px;
        text-align: center
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
