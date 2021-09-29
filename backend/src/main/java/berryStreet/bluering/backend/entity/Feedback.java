package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Feedback {
    private int FID;
    private String scoreRange;
    private String remark;
    private int quiz_feed_ID;

//    public Feedback(int fid,String scoreRange, String remark, int quiz_feed_ID) {
//        this.scoreRange = scoreRange;
//        this.remark = remark;
//        this.quiz_feed_ID = quiz_feed_ID;
//        this.FID=fid;
//    }
//    public Feedback(){
//
//    }
    @Override
    public String toString() {
        return "Feedback{" +
                "FID=" + FID +
                ", scoreRange='" + scoreRange + '\'' +
                ", remark='" + remark + '\'' +
                ", quiz_feed_ID=" + quiz_feed_ID +
                '}';
    }

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public String getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(String scoreRange) {
        this.scoreRange = scoreRange;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getQuiz_feed_ID() {
        return quiz_feed_ID;
    }

    public void setQuiz_feed_ID(int quiz_feed_ID) {
        this.quiz_feed_ID = quiz_feed_ID;
    }
}
