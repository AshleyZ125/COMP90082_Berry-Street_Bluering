package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class RecordVO {
    private int RID;
    private List<QuizSelection> quizContent;
    private String savedReflection;
    private String feedback;
    private String quizTopic;
    private int userID;
    private Date rDate;
}
