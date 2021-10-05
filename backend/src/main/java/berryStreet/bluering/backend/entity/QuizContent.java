package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizContent {
    private String qContent;
    private String option;
}
