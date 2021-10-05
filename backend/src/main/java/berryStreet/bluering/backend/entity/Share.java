package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Share {
    private int SID;
    private int sender;
    private int receiver;
    private String shareReflection;
    private int recordID;

    private String quizTopic;
    private Date rDate;

    private String username;

}
