package berryStreet.bluering.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@ConfigurationProperties("admin")
@Data
@Component
public class AdminConfig {
    private Set<String> inviteCodes;
}
