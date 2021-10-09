<template>
    <div class='main'>
        <SpaceHeader></SpaceHeader>
        <div class='quizContent'>

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
                tableData:[{
                    RID:1,
                    savedReflection:"234",
                    rFeedBack:"234",
                    rTopic:"234",
                    userID:2,
                    rDate:"",
                    quizContent:[{"qContent":"234","option":"234"}]
                }]
            };
        },
        components:{
            SpaceHeader,
            FeedbackFooter,
        },

        mounted() {
            this.allAjax();
    },
    }    
</script>
