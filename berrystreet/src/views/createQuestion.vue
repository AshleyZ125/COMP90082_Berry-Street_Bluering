<template>
  <div class="createQuestion">
      <main-header title="Create Quiz"></main-header>
      <div class="pageBody">
          <div class="pageContainer">
             <div style="height:50px"><el-button type="warning" style="float:right;" @click.native="addFormVisible = true">Create new question</el-button></div>
              <el-table :data="allForm" style="width: 100%" :header-cell-style="{background:'#eee',color:'#606266',fontSize: '18px'}" empty-text="No questions yet!">
                    <el-table-column label="Question Name" width="180" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:14px">{{ scope.row.qcontent }}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="Option 0" width="180" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:14px">{{ scope.row.options[0].value }}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="Option 1" width="180" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:14px">{{ scope.row.options[1].value }}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="Operations" align="center">
                        <template slot-scope="scope">
                            <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                            <el-button size="mini" type="danger" @click="handleDel(scope.$index, scope.row)">Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>
              <el-dialog title="Create a question" :visible.sync="addFormVisible" :close-on-click-modal="false">
                <el-form :model="addForm" label-width="80px" ref="addForm">
                    <el-form-item label="Question" prop="qcontent" :rules="{required: true, message: 'Question name can not be null', trigger: 'blur'}">
                        <el-input v-model="addForm.qcontent" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item
                        v-for="(option, index) in addForm.options"
                        :label="'Option' + index"
                        :key="option.key"
                        :prop="'options.' + index + '.value'"
                        :rules="{
                        required: true, message: 'option can not be null', trigger: 'blur'
                        }"
                    >
                        <el-input v-model="option.value" style="width:400px"></el-input>
                        <el-form-item label="Point" :prop="'options.' + index + '.point'" :rules="[{ required: true, message: 'Point is required'},{ type: 'number', message: 'Point must be a number'}]" style="display:inline-block;margin-right:20px">
                            <el-input v-model.number="option.point" style="width:50px" ></el-input>
                        </el-form-item>
                        <el-button @click.prevent="removeOption(option)" v-if="option.key!==0&&option.key!==1" >Delete</el-button>
                    </el-form-item>
                    
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm('addForm')">Complete</el-button>
                    <el-button  type="success" @click="addOption">New Option</el-button>
                    <el-button @click="resetForm('addForm')">Reset</el-button>
                    <el-button @click.native="addFormVisible = false">Cancel</el-button>
                </div>
             </el-dialog>
             <el-dialog title="Edit a question" :visible.sync="editFormVisible" :close-on-click-modal="false">
                <el-form :model="editForm" label-width="80px" ref="editForm">
                    <el-form-item label="Question" prop="qcontent" :rules="{required: true, message: 'Question name can not be null', trigger: 'blur'}">
                        <el-input v-model="editForm.qcontent" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item
                        v-for="(option, index) in editForm.options"
                        :label="'Option' + index"
                        :key="option.key"
                        :prop="'options.' + index + '.value'"
                        :rules="{
                        required: true, message: 'option can not be null', trigger: 'blur'
                        }"
                    >
                        <el-input v-model="option.value" style="width:400px"></el-input>
                        <el-form-item label="Point" :prop="'options.' + index + '.point'" :rules="[{ required: true, message: 'Point is required'},{ type: 'number', message: 'Point must be a number'}]" style="display:inline-block;margin-right:20px">
                            <el-input v-model.number="option.point" style="width:50px" ></el-input>
                        </el-form-item>
                        <el-button @click.prevent="removeEditOption(option)" v-if="option.key!==0&&option.key!==1">Delete</el-button>
                    </el-form-item>
                    
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submiteditForm('editForm')">Complete</el-button>
                    <el-button  type="success" @click="addEditOption">New Option</el-button>
                    <el-button @click.native="editFormVisible = false">Cancel</el-button>
                </div>
             </el-dialog>
          </div>
      </div>
  </div>
</template>

<script>
import MainHeader from './../components/MainHeader'
export default {
    name:'createquiztitle',
    data(){
        return{
           QID:'',
           questionId:'',
           addLoading:false,
           addFormVisible:true,
           editFormVisible:false,
           addForm:{
               qcontent:'',
               options:[{
                   key:0,
                   value:'',
                   point:0,
               },{
                   key:1,
                   value:'',
                   point:0,
               },
               ]
           },
           editForm:{
               qcontent:'',
               options:[{
                   key:0,
                   value:'',
                   point:0,
               },
               {
                   key:1,
                   value:'',
                   point:0,
               },]
           },
           allForm:[],

        }
    },
    components:{
        MainHeader
    },
    mounted(){
        this.QID = this.$route.params.QID;
        this.getQuestions();
    },
    methods:{
        getQuestions(){
            let QID = this.QID;
            this.axios.get(`/api/quiz/getQuestion/${QID}`).then((res)=>{
                //console.log(res)
                this.allForm = res.data.data
                    
            })
        },
        cancelCreate(){
            this.$confirm('You have not finished yet. Are you sure you wanna cancel it?', 'Tips', {
                type: 'warning',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.$router.push('/myquiz')
            })
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.axios.post('/api/quiz/setQuestion',{
                    quizID:this.QID,
                    qContent:this.addForm.qcontent,
                    options:this.addForm.options
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'New question added!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.$refs['addForm'].resetFields()
                        this.addFormVisible = false
                        this.getQuestions()
                        
                    }
                    else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
            } else {
                this.$message({
                    showClose: true,
                    message: 'Oops, Please check the information is valid',
                    type: 'error'
                });
                return false;
            }
            });
      },
      submiteditForm(formName){
          this.$refs[formName].validate((valid) => {
            if (valid) {
                console.log(this.editForm)
                this.axios.post('/api/quiz/setQuestion',{
                    quizID:this.QID,
                    qID:this.questionId,
                    qContent:this.editForm.qcontent,
                    options:this.editForm.options
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'Question updated!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.$refs['editForm'].resetFields()
                        this.editFormVisible = false
                        this.getQuestions()
                        
                    }
                    else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
            } else {
                this.$message({
                    showClose: true,
                    message: 'Oops, Please check the information is valid',
                    type: 'error'
                });
                return false;
            }
            });
      },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        removeOption(item) {
            var index = this.addForm.options.indexOf(item);
            if (index !== -1) {
            this.addForm.options.splice(index, 1);
            }
        },
        removeEditOption(item) {
            var index = this.editForm.options.indexOf(item);
            if (index !== -1) {
            this.editForm.options.splice(index, 1);
            }
        },
        addOption() {
            this.addForm.options.push({
            key: Date.now(),
            value: '',
            point:0
            });
        },
        addEditOption() {
            this.editForm.options.push({
            key: Date.now(),
            value: '',
            point:0
            });
        },
        handleEdit: function (index, row) {
            this.editFormVisible = true
            this.questionId=row.qid;
            this.editForm = Object.assign({}, row)
        },
        handleDel: function (index, row) {
            this.$confirm('Are you sure you wanna delete this question? You can not undo this action!', 'Warning', {
                type: 'warning',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.questionId=row.qid;
                this.axios.post('/api/quiz/setQuestion',{
                    qID:this.questionId,
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'Question deleted!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.getQuestions()
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
        
        
    }
}
</script>

<style lang="scss">
.createQuizTitle{
    font-family: 'Acme', sans-serif; 
    .pageBody{
            width: 100%;
            .pageContainer{
                width: 1300px;
                margin: 30px auto;
            }

        }
        .el-form-item__label{
            font-size: 25px;
            font-family: 'Acme', sans-serif;  
            color: black;
        }
}
</style>