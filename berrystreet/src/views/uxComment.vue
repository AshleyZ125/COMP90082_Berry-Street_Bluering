<template>
  <div class="mylecs">
      <main-header title="UX Comments"></main-header>
      <div class="pageBody">
          <div class="pageContainer">
            <el-table :data="allLec" style="width: 100%" empty-text="You don't have any LECs yet!"  :header-cell-style="{background:'#eee',color:'#606266',fontSize: '20px'}">
                <el-table-column label="Date" align="center">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <span style="font-size:24px">{{ scope.row.rdate }}</span>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="Content" align="center">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <span style="font-size:24px">{{ scope.row.rtopic }}</span>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" type="primary" @click="gotoLecDetail(scope.$index, scope.row)">View</el-button>
                    </template>
                </el-table-column>
            </el-table>
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
    name:'uxcomment',
    data(){
        return{
            userId:'',
            allLec:[
                {
                    username:'tommy',
                    topic:'cooking',
                    date:'2021-02-04'
                }
            ]
        }
    },
    components:{
        MainHeader,
    },
    mounted(){
        this.getCommentList();
    },
    methods:{
        
     getCommentList(){
          let userId = this.$cookie.get('userId');
          console.log(userId)
            this.axios.get(`/api/record/sharedList/${userId}`).then((res)=>{
                console.log(res)
                this.allLec = res.data.data;
            })
      },
      gotoLecDetail(index, row) {
          this.$router.push({
                path: '/lecDetail/'+row.username+'/'+row.sid,
            });
      },
    goBack(){
        this.$router.push('/myspace')
    }

      
    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');
    .mylecs{
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