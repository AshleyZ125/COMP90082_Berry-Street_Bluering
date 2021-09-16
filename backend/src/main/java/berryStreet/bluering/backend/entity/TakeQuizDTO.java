package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class TakeQuizDTO {
    private int QID;
    private String topic;
    private String overview;
    List<Question> questionList;
}
