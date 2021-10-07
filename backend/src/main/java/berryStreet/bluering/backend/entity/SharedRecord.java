package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.time.LocalDate;

@Data
@Builder
public class SharedRecord {
    private int SID;
    private String rTopic;
    private LocalDate rDate;
    private String username;
}
