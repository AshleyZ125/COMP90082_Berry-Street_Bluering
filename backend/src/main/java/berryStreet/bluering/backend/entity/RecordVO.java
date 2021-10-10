package berryStreet.bluering.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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