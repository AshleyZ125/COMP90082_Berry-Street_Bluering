package berryStreet.bluering.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private int RID;
    private String quizContent;
    private String savedReflection;
    private String rFeedback;
    private String rTopic;
    private int userID;
    private LocalDate rDate;
}
