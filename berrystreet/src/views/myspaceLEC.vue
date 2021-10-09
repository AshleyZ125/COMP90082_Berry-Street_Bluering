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
                <!-- <el-dropdown-item><a href="/">Account Settings</a></el-dropdown-item> -->
            </el-dropdown-menu>
          </el-dropdown>
        </div>

        <div class ="space-container">
            <el-table 
                :data="tableData"
                style="width: 100%"> <!-- :span= "1" v-for="item in tableData"    :key="item.quizTopic " :offset="1"  -->
                
                <el-table-column
                    prop="rTopic"
                    label="Quiz Topic"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="rDate"
                    label="Date"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="view"
                    label="View">
                    <el-button @click="gotoView(item)" type="text" > View </el-button>
                    
                </el-table-column>
            </el-table>
        </div>

        <FeedbackFooter></FeedbackFooter>
    </div>
</template>

<script>

import SpaceHeader from "./../components/SpaceHeader.vue"
import FeedbackFooter from "./../components/FeedbackFooter.vue"

export default {
    name: "myspaceLEC",
    data(){
        return{
            userId:'',
            tableData:[{
                RID:1,
                savedReflection:"234",
                rFeedBack:"234",
                rTopic:"234",
                userID:2,
                rDate:"",
                quizContent:[{"qContent":"234","option":"234"}]
                // quizTopic: 'Collaborative learning',
                // qid:'1',
                // date: '2021-01-01',
                // view: 'view'
             }, {
                RID:1,
                savedReflection:"234",
                rFeedBack:"234",
                rTopic:"tranining",
                userID:2,
                rDate:"2021-02-02",
                quizContent:[{"qContent":"234","option":"234"}],
            } ]
         };
    },
    components:{
        SpaceHeader,
        FeedbackFooter,
    },

    mounted() {
        this.userid = this.$route.params.userID
        this.allAjax();
    },

    methods:{
        gotoReset(){
            this.$router.push('/resetPassword')
        },

        gotoResetEmail(){
            this.$router.push('/resetEmail')
        },

        gotoView(item){
            alert("go to see pastquiz and feedback and diary")                        // add 跳转页面
            this.$router.push('/pastFeedbackLEC')
        },

        allAjax() {
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

</style>