package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Question {
    private int qID;
    private String qContent;
    //"options":[{"content":"aaaaaa","point":1},
    //                      {"content":"bbbbbb","point":2}]
    private String options;
    private int quizID;

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qID=" + qID +
                ", qContent='" + qContent + '\'' +
                ", options='" + options + '\'' +
                ", quizID=" + quizID +
                '}';
    }
}
