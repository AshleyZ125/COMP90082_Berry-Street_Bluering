package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizSelection {
    private String qContent;
    private String option;
}
