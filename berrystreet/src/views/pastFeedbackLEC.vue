<template>
    <div class='main'>
        <SpaceHeader></SpaceHeader>
        <div class='quiz'>
            <el-table
                :data = 'tableData.quizContent'
                style = "width: 100%"
                empty-text="You don't have records yet!">
                <el-table-column label="Question">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <span style="font-size:24px">{{ scope.row.content }}</span>
                        </div>
                    </template>
                </el-table-column>

                <el-table-column prop="option" label="Answer">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <span style="font-size:24px">{{ scope.row.option }}</span>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div class = "feedback">
            <h2 class = "feedbackTitle"> Feedback </h2>

            <p class = "feedbackContent"> {{feedback}} </p>
        </div>

        <div class = "reflection">
            <h2 class = "reflectionTitle"> Reflection diary </h2>

            <p class = "reflectionContent"> {{reflection}} </p>
        </div>

        <div class = "backButton">
            <el-button @click="goBack()" style="margin:20px 0 0 100px">Back</el-button>
        </div>
        <FeedbackFooter></FeedbackFooter>
    </div>
</template>

<script>
    import SpaceHeader from "./../components/SpaceHeader.vue"
    import FeedbackFooter from "./../components/FeedbackFooter.vue"

    export default {
        name: "pastFeedbackLEC",
        data(){
            return{
                feedback:'',
                reflection:'',
                tableData:[{
                    // RID:1, //UID, QID, rDate 
                    // savedReflection:"234",
                    // rFeedBack:"234",
                    // rTopic:"234",
                    // userID:2,
                    // rDate:"",
                    // quizContent:[{"qContent":"234","option":"234"}]
                }]
            };
        },
        components:{
            SpaceHeader,
            FeedbackFooter,
        },

        mounted() {
            this.RID = this.$route.params.RID
            this.userId = this.$route.params.userId
            this.allAjax();
        },

        methods:{
            allAjax(){
                console.log("RID 是", this.RID)
                console.log("userId 是", this.userId)
                this.axios.get(`/api/record/getRecord/${this.RID}`).then((res) => {  //API need insert
                console.log("res = ", res);
                let data = res.data.data;                                                                            // check the path
                this.tableData = data;
                this.feedback = data.feedback;
                this.reflection = data.savedReflection;
                
            });
            },

            goBack(){
                this.$router.push({
                    name: 'myspaceLEC',
                    params: {
                        userId: this.userId
                    }
                })
            }
        }
    }    
</script>

<style lang="scss">
    @import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');

    .main{
        .quiz{
            // position: absolute;
            // left: 50%;
            width: 80%;
            margin: 40px auto;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
        }
        .feedback{
            font-family: 'Acme', sans-serif; 
            font-size: 20px;
            width: 80%; 
            border-radius: 15px;
            margin: 40px auto;
            // background-color: #50A7C2;
            .feedbackTitle{
                 text-align: center;
            }
            .feedbackContent{
                box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
                border-radius: 15px;
            }
        }
        .reflection{
            font-family: 'Acme', sans-serif;
            font-size: 20px;
            width: 80%;
            margin: 40px auto;
            .reflectionTitle{
                text-align: center;
            }
            .reflectionContent{
                box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
                border-radius: 15px;
            }
        }
        .backButton{
            margin: 40px auto;
            text-align: center;
        }
    }
</style>


