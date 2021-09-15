<template>
    <div class="quizcard" @mouseenter="onMouseoverEnvDelBtn($event)" @mouseleave="onMouseleaveEnvDelBtn($event)" :id=id>
        <div class="buttondiv"><el-button type="warning" class="flagButton" round size="small">Make Private</el-button></div>
        <div class="titlediv"><h1>Leadership</h1></div>
        <div class="bottomdiv">
        <div style="width: 50%;">
            <el-button type="warning" class="flagButton1" circle >Edit</el-button>
        </div>
        <div style="width: 50%;">
            <el-button type="danger" @click="quizdelete()" class="flag" icon="el-icon-delete" circle style="margin-bottom: 10px;"></el-button>
        </div>
            
        </div>
    </div>
</template>

<script>
export default {
    name:'quiz-card',
    data(){
        return{
            class:'.flag',
            id:100,
        }
    },
    methods:{
        quizdelete(){
            alert("11")
        },
        onMouseoverEnvDelBtn(event) {
            event.target.parentElement.querySelector(".flag").style.cssText += "display:block"
            event.target.parentElement.querySelector(".flagButton").style.cssText += "display:block"
           event.target.parentElement.querySelector(".flagButton1").style.cssText += "display:block"
        },
        onMouseleaveEnvDelBtn(event) {
            event.target.parentElement.querySelector(".flag").style.cssText += "display:none"
            event.target.parentElement.querySelector(".flagButton").style.cssText += "display:none"
            event.target.parentElement.querySelector(".flagButton1").style.cssText += "display:none"
        },
    }

}
</script>

<style lang="scss">
    .quizcard{
        width: 300px;
        height: 150px;
        border-radius: 15px;
        background-image: linear-gradient(120deg, #a1c4fd 0%, #a1c4fd 100%);
        color: white;
        cursor: pointer;
        margin-right: 10px;
        .buttondiv{
            height:40px;
            position: relative;
            .flagButton{
                display: none;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
        }
        .titlediv{
            text-align: center;
            height: 70px;
            display:flex;
            align-items: center;
            justify-content: center;
            h1{
                font-size: 40px;
            }
        }
        .bottomdiv{
            height:40px;
            display: flex;
            .flag{
                display: none;
                float: right;
                
                
            }
            .flagButton1{
                display: none;
                
            }
        }
        
        

        
    }
    
</style>