package berryStreet.bluering.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String feedback;
    private String topic;
    private int userID;
    private LocalDate date;

    //private String shareReflection;
    //private String rFeedback;
}
