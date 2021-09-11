package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionVO {
    private int qID;
    private String qContent;
    private List<Option> options;
    private int quizID;
}
