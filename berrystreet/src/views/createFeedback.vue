<template>
    <div class="createFeedback">
        <main-header title="Create Feedback"></main-header>
        <div class="pageBody">
          <div class="pageContainer">
             <div style="height:50px"><el-button type="warning" style="float:right;margin-top:10px;margin-bottom:10px" @click.native="addFormVisible = true">Create new feedback</el-button></div>
              <el-table :data="feedForm" style="width: 100%" :header-cell-style="{background:'#eee',color:'#606266',fontSize: '18px'}" empty-text="No feedbacks yet!">
                    <el-table-column label="Feedback" width="400" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:14px">{{ scope.row.remark }}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="From" width="250" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:14px">{{ scope.row.from }}</span>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="To" width="250" align="center">
                        <template slot-scope="scope">
                            <div slot="reference" class="name-wrapper">
                                <span style="font-size:14px">{{ scope.row.to }}</span>
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
                <div style="text-align:center; margin-top:20px">
                    <el-button style="margin-right:50px" @click="cancelCreate">Cancel</el-button>
                    <el-button type="primary" @click="completeQuiz()">Finish</el-button>
                </div>
                

              <el-dialog title="Create a feedback" :visible.sync="addFormVisible" :close-on-click-modal="false" >
                <el-form :model="addForm" label-width="150px" ref="addForm" :rules="FormRules">
                        <el-form-item  label="Feedback" prop="remark" :rules="{required: true, message: 'feedback can not be null', trigger: 'blur'}">
                            <el-input v-model="addForm.remark" type="textarea" :rows="3" placeholder="Please input feedback description"></el-input>
                        </el-form-item>
                        <el-form-item label="From" prop="from">
                            <el-input v-model.number="addForm.from" style="width:70px"></el-input>
                        </el-form-item>
                        <el-form-item label="To" prop="to">
                            <el-input v-model.number="addForm.to" style="width:70px"></el-input>
                        </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm('addForm')">Complete</el-button>
                    <el-button @click="resetForm('addForm')">Reset</el-button>
                    <el-button @click.native="addFormVisible = false">Cancel</el-button>
                </div>
             </el-dialog>
             <el-dialog title="Edit a feedback" :visible.sync="editFormVisible" :close-on-click-modal="false">
                <el-form :model="editForm" label-width="150px" ref="editForm" :rules="editFormRules">
                        <el-form-item  label="Feedback" prop="remark" :rules="{required: true, message: 'feedback can not be null', trigger: 'blur'}">
                            <el-input v-model="editForm.remark" type="textarea" :rows="3" placeholder="Please input"></el-input>
                        </el-form-item>
                        <el-form-item label="From" prop="from">
                            <el-input v-model.number="editForm.from" style="width:70px"></el-input>
                        </el-form-item>
                        <el-form-item label="To" prop="to">
                            <el-input v-model.number="editForm.to" style="width:70px"></el-input>
                        </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submiteditForm('editForm')">Complete</el-button>
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
    name:'createfeedback',
    components:{
        MainHeader
    },
    data(){
        let fromV = (rule,value,callback) => {
            if (value === '') {
                callback(new Error('Please input the from point'));
            }else if(!Number.isInteger(value)) {
                callback(new Error('Please input digits'));
            }
            else if(Number(value)>=Number(this.addForm.to)) {
                callback(new Error('The Minimum value cannot be equal to or greater than Maximum value!'));
            }else {
                callback()
            }
        }
        let toV = (rule,value,callback) => {
            if (value === '') {
                callback(new Error('Please input the from point'));
            }else if(!Number.isInteger(value)) {
                callback(new Error('Please input digits'));
            }
            else if(Number(value)<=Number(this.addForm.from)) {
                callback(new Error('The Maximum value cannot be equal to or smaller than Minimum value!'));
            }else {
                callback()
            }
        }
        let editfromV = (rule,value,callback) => {
            if (value === '') {
                callback(new Error('Please input the from point'));
            }else if(!Number.isInteger(value)) {
                callback(new Error('Please input digits'));
            }
            else if(Number(value)>=Number(this.editForm.to)) {
                callback(new Error('The Minimum value cannot be equal to or greater than Maximum value!'));
            }else {
                callback()
            }
        }
        let edittoV = (rule,value,callback) => {
            if (value === '') {
                callback(new Error('Please input the from point'));
            }else if(!Number.isInteger(value)) {
                callback(new Error('Please input digits'));
            }
            else if(Number(value)<=Number(this.editForm.from)) {
                callback(new Error('The Maximum value cannot be equal to or smaller than Minimum value!'));
            }else {
                callback()
            }
        }
        return{
            feedbackId:'',
            feedForm:[],
            addFormVisible:true,
            editFormVisible:false,
            addForm:{
               remark:'',
               from:0,
               to:0
           },
           editForm:{
               remark:'',
               from:0,
               to:0
           },
           FormRules:{
                from: [{validator:fromV,trigger:'blur'}],
                to: [{validator:toV,trigger:'blur'}],
            },
            editFormRules:{
                from: [{validator:editfromV,trigger:'blur'}],
                to: [{validator:edittoV,trigger:'blur'}],
            }
        }
    },
    mounted(){
        //this.QID = this.$route.params.QID;
        this.QID=this.$route.params.id
        this.getFeedbacks();
    },
    methods:{
        completeQuiz(){
            this.axios.get(`/api/quiz/checkQuizStatus/${this.QID}`).then((res)=>{
                
                this.$router.push('/myquiz')
            })
        },
        getFeedbacks(){
            let QID = this.QID;
            this.axios.get(`/api/quiz/getFeedback/${QID}`).then((res)=>{
                for(let i=0;i<res.data.data.length;i++){
                    let feedbackObj={};
                    feedbackObj.remark=res.data.data[i].remark;
                    let scorerange = res.data.data[i].scoreRange.split(";");
                    feedbackObj.from = Number(scorerange[0]);
                    feedbackObj.to = Number(scorerange[1]);
                    feedbackObj.id=res.data.data[i].fid;
                    this.feedForm.push(feedbackObj)
                } 
                //console.log(this.feedForm)
            })
        },
        cancelCreate(){
            this.$confirm('You have not finished yet. Are you sure you wanna cancel it?', 'Tips', {
                type: 'warning',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.axios.get(`/api/quiz/checkQuizStatus/${this.QID}`).then((res)=>{
                    console.log(res.data)
                    // if(res.data.status!==0){
                    //     this.axios.post('/api/quiz/setQuizStatus',{
                    //         QID:this.QID,
                    //         status:1
                    //     }).then((res)=>{
                    //             this.$message({
                    //                 message: res.data.msg,
                    //                 showClose: true,
                    //                 type: 'success', 
                    //             }) 
                    //     })
                    // }
                    
                })
                this.$router.push('/myquiz')
            })
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.axios.post('/api/quiz/setFeedback',{
                    quiz_feed_ID:this.QID,
                    remark:this.addForm.remark,
                    scoreRange:this.addForm.from.toString().concat(";",this.addForm.to.toString())
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'New Feedback added!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.$refs['addForm'].resetFields()
                        this.addFormVisible = false
                        this.feedForm=[];
                        this.getFeedbacks()
                        
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
                console.log(this.feedbackId)
                this.axios.post('/api/quiz/setFeedback',{
                    FID:this.feedbackId,
                    scoreRange:this.editForm.from.toString().concat(";",this.editForm.to.toString()),
                    remark:this.editForm.remark,
                    quiz_feed_ID:this.QID
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'Feedback updated!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.$refs['editForm'].resetFields()
                        this.editFormVisible = false
                        this.feedForm=[];
                        this.getFeedbacks()
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
      handleEdit: function (index, row) {
            this.editFormVisible = true
            this.feedbackId=row.id;
            this.editForm = Object.assign({}, row)
        },
        handleDel: function (index, row) {
            this.$confirm('Are you sure you wanna delete this feedback? You can not undo this action!', 'Warning', {
                type: 'warning',
                confirmButtonText: 'Yes, I am sure!',
                cancelButtonText: 'Cancel',
            }).then(() => {
                this.feedbackId=row.id;
                this.axios.post('/api/quiz/setFeedback',{
                    FID:this.feedbackId,
                }).then((res)=>{
                    console.log(res)
                    if(res.status==200){
                        this.$message({
                            message: 'Feedback deleted!',
                            showClose: true,
                            type: 'success', 
                        })
                        this.feedForm=[];
                        this.getFeedbacks()
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
      resetForm(formName) {
            this.$refs[formName].resetFields();
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