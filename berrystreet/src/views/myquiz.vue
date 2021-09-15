<template>
  <div class="myquiz">
      <main-header title="My Quizzes"></main-header>
      <div class="pageBody">
          <div class="pageContainer">
              <div style="height:50px"><el-button type="warning" style="float:right;" @click="gotoCreateQuizTitle()">Create new quiz</el-button></div>
              <div style="display:flex">
                  <div class="public">
                    <h2>Public</h2>
                    <el-table :data="publicData" style="width: 100%" :row-class-name="tableRowClassName" :header-cell-style="{background:'#eee',color:'#606266',fontSize: '18px'}">
                        <el-table-column label="Quiz Name" width="180" class="success-row" align="center">
                            <template slot-scope="scope">
                                <div slot="reference" class="name-wrapper">
                                    <span style="font-size:30px">{{ scope.row.name }}</span>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="Operations" align="center">
                            <template slot-scope="scope">
                                <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                                <el-button size="mini" type="info" @click="handlePrivate(scope.$index, scope.row)">Make Private</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div class="private">
                    <h2>Private</h2>
                    <el-table :data="privateData" style="width: 100%" :row-class-name="tableRowClassNamePrivate" :header-cell-style="{background:'#eee',color:'#606266',fontSize: '18px'}">
                        <el-table-column label="Quiz Name" width="180" class="success-row" align="center">
                            <template slot-scope="scope">
                                <div slot="reference" class="name-wrapper">
                                    <span style="font-size:30px">{{ scope.row.topic }}</span>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="Operations" align="center">
                            <template slot-scope="scope">
                                <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                                <el-button size="mini" type="success" @click="handlePrivate(scope.$index, scope.row)">Make Public</el-button>
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
            publicData: [
            {
            name: 'Leadership',
            id: '111'
            }, {
            name: 'Leadership',
            id: '2'
            }, {
            name: 'Leadership',
            id: '3'
            }, {
            name: 'Leadership',
            id: '4'
            }],
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
            })
      },
        handleEdit(index, row) {
        alert(index, row);
      },
      handleDelete(index, row) {
        //alert(index, row);
        alert(row.id);
      },
      handlePrivate(index, row) {
        alert(index, row);
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