package berryStreet.bluering.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareVO {
    private int sender;
    private String receiver;
    private String shareReflection;
    private List<QuizSelection> quizContent;
    private String feedback;
    private String topic;
    private int userID;
    private LocalDate date;
}
