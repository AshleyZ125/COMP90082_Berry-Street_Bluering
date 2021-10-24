package berryStreet.bluering.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizSelection {
    //"qContent":"b"
    private String content;
    //{"content":"aaaaaa","point":1}
    private String option;
}
