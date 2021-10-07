package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Share {
    private int SID;
    private int sender;
    private int receiver;
    private int recordID;
    private String shareReflection;
}
