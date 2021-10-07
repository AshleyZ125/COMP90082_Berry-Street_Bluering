package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class Record {
    private int RID;
    private String quizContent;
    private String savedReflection;
    private String rFeedback;
    private String rTopic;
    private int userID;
    private LocalDate rDate;
}
