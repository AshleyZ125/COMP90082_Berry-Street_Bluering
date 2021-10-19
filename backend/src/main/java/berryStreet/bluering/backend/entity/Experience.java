package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Experience {
    private int EID;
    private String eContent;
    private String eDate;
}
