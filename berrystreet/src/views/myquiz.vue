<template>
  <div class="myquiz">
      <main-header title="My Quizzes"></main-header>
      <div class="pageBody">
          <div class="pageContainer">
              <div style="height:50px"><el-button type="warning" style="float:right;" @click="gotoCreateQuizTitle()">Create new quiz</el-button></div>
              <div style="display:flex">
                  <div class="public">
                    <h2>Public</h2>
                    <el-table :data="publicData" style="width: 100%" :row-class-name="tableRowClassName" empty-text="No public quiz yet!" :header-cell-style="{background:'#eee',color:'#606266',fontSize: '18px'}">
                        <el-table-column label="Quiz Name"  class="success-row" align="left">

                            <template slot-scope="scope">
                                <el-popover
                                placement="top-start"
                                title="Overview"
                                width="300"
                                trigger="hover">
                                    <p style="word-wrap:break-word;word-break:normal;">{{ scope.row.overview }}</p>
                                    <div slot="reference" class="name-wrapper">
                                        <span style="font-size:24px">{{ scope.row.topic }}</span>
                                    </div>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column label="Operations" align="center" width="290">
                            <template slot-scope="scope">
                                <!-- <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                                <el-button size="mini" type="danger" @click="handleDel(scope.$index, scope.row)">Delete</el-button> -->
                                <el-button size="mini" type="info" @click="makePrivate(scope.$index, scope.row)">Make Private</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div class="private">
                    <h2>Private</h2>
                    <el-table :data="privateData" style="width: 100%" :row-class-name="tableRowClassNamePrivate" empty-text="No private quiz yet!" :header-cell-style="{background:'#eee',color:'#606266',fontSize: '18px'}">
                        <el-table-column label="Quiz Name" class="success-row" align="left">
                            <template slot-scope="scope">
                                <el-popover
                                placement="top-start"
                                title="Overview"
                                width="300"
                                trigger="hover">
                                    <p style="word-wrap:break-word;word-break:normal;">{{ scope.row.overview }}</p>
                                    <div slot="reference" class="name-wrapper">
                                        <span style="font-size:24px">{{ scope.row.topic }}</span>
                                    </div>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column label="Status" align="center">
                            <template slot-scope="scope">
                                <el-tag size="small" type="danger" v-show="scope.row.status===1">In Progress</el-tag>
                                <el-tag size="small" type="success" v-show="scope.row.status===2">Completed</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column label="Operations" align="center" width="290">
                            <template slot-scope="scope">
                                <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                                <el-button size="mini" type="danger" @click="handleDel(scope.$index, scope.row)">Delete</el-button>
                                <el-button size="mini" type="success" @click="makePublic(scope.$index, scope.row)">Make Public</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
              </div>
              
          </div>
      </div>
  </div>
</template>

<script>
import MainHeader from './../components/MainHeader.vue'
export default {
    name:'myquiz',
    data(){
        return{
            userId:'',
            publicData: [],
            privateData:[]
            
        }
    },
    components:{
        MainHeader,
    },
    mounted(){
        this.getQuizList();
    },
    methods:{
        tableRowClassName({row, rowIndex}) {
          return 'success-row';
      },
      tableRowClassNamePrivate({row, rowIndex}) {
          return 'warning-row';
      },
      getQuizList(){
          let userId = this.$cookie.get('userId');
            this.axios.get(`/api/quiz/superQuizList/${userId}`).then((res)=>{
                this.privateData = res.data.data.private  
                this.publicData = res.data.data.public
            })
      },
        handleEdit(index, row) {
            //console.log(row)
            this.$router.push({
                path: '/editquiztitle/'+row.qid,
            });
      },
      handleDel: function (index, row) {
            this.$confirm('Are you sure you wanna delete this quiz? You can not undo this action!', 'Warning', {
                type: 'warning',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.axios.post('/api/quiz/setQuiz',{
                    QID:row.qid,
                    status:4
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'Quiz deleted!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.getQuizList()
                    }
                    else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
            }).catch(() => {
            })
        },
      makePrivate(index, row) {
        this.$confirm('You are making this quiz private, Continue?', '', {
                type: 'Info',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.axios.post('/api/quiz/setQuizStatus',{
                    QID:row.qid,
                    status:2
                }).then((res)=>{
                    console.log(res)
                    if(res.data.status==0){
                        this.$message({
                            message: 'Quiz has been set to private!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.getQuizList()
                    }
                    else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
            }).catch(() => {
            })
      },
      makePublic(index, row) {
        this.$confirm('You are making this quiz public, Continue?', '', {
                type: 'Info',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.axios.post('/api/quiz/setQuizStatus',{
                    QID:row.qid,
                    status:3
                }).then((res)=>{
                    console.log(res)
                    if(res.data.status==0){
                        this.$message({
                            message: 'Quiz has been set to public!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.getQuizList()
                    }
                    else{
                        this.$message({
                            message: 'The quiz has not been finished yet, please finish it first!',
                            type: 'error'
                        })
                    }
                })
            }).catch(() => {
            })
      },
      gotoCreateQuizTitle(){
          this.$router.push('/createquiztitle')
      }

      
    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .myquiz{
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
                .public, .private{
                    width: 100%;
                    height: 300px;
                    
                }
            }

        }
         .el-table .warning-row {
            background: oldlace;
        }
        .el-table .success-row {
            background: #f0f9eb;
        }
        
    }
</style>