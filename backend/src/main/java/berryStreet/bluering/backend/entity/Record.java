package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Record {
    private int RID;
    private String quizContent;
    private String savedReflection;
    private String feedback;
    private String quizTopic;
    private int userID;
    private Date rDate;
}
