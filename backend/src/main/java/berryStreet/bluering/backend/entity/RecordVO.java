package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class RecordVO {
    private int RID;
    private List<QuizSelection> quizContent;
    private String savedReflection;
    private String rFeedback;
    private String rTopic;
    private int userID;
    private LocalDate rDate;

    //private String shareReflection;
    //private String rFeedback;
}
