package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizSelection {
    //"qContent":"b"
    private String qContent;
    //{"content":"aaaaaa","point":1}
    private Option option;
}
