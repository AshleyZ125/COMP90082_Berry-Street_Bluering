<template>
  <div class="footerDiv">
      <div class="bottom">
        <div class="desc">
           <p style = "font-family: 'Acme', sans-serif;font-size:15px; cursor:pointer;" @click="gotoComment">
              How do you feel about using LEx Mirror? Tell us how you think we can improve 
            </p>
        </div>
    </div>
    <el-dialog title="Please tell us how you think we can improve LEx Mirror" :visible.sync="addFormVisible" :close-on-click-modal="false" >
        <el-form :model="addForm" label-width="150px" ref="addForm">
                <el-form-item  label="Feedback" prop="comment" :rules="{required: true, message: 'Comments can not be null', trigger: 'blur'}">
                    <el-input v-model="addForm.comment" type="textarea" :rows="5" placeholder="Please provide your comments"></el-input>
                </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm('addForm')">Submit</el-button>
            <el-button @click="resetForm('addForm')">Reset</el-button>
            <el-button @click.native="addFormVisible = false">Cancel</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
    name:'ux-footer',
    data(){
        return{
            addFormVisible:false,
            addForm:{
                comment:''
            }
        }
    },
    methods:{
            gotoComment(){
                this.addFormVisible = true;
            },
            submitComment(){
                console.log(this.addForm.comment)
                this.axios.post('/api/userExp/addUserExp',{
                    eContent:this.addForm.comment,
                }).then((res)=>{
                    if(res.data.status==0){
                        this.$message({
                            message: 'Thank you for your comments! We will use your suggestion to improve LEx Mirror.',
                            type: 'success',
                            showClose: true,
                        })
                        this.addFormVisible = false;
                    }
                    else{
                        this.$message({
                            message: res.data.msg,
                            type: 'error'
                        })
                    }
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.submitComment();
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


    }
}
</script>

<style lang="scss">
    .bottom{
    position: fixed;
    bottom: 0;
    left: 0;
    height: 50px;
    width: 100%;
    background: #50A7C2;
    display: flex;
    color: white;
    justify-content: center;
    align-items: center;
}
</style>