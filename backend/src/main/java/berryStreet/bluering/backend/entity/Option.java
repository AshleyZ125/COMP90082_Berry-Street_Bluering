package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Option {
    private int key;
    private String content;
    private int point;
}
