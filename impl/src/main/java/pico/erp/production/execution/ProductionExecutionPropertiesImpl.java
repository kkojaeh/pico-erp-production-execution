package pico.erp.production.execution;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pico.erp.shared.Public;
import pico.erp.user.group.GroupData;

@Public
@Data
@Configuration
@ConfigurationProperties("production-execution")
public class ProductionExecutionPropertiesImpl implements ProductionExecutionProperties {

  GroupData executorGroup;

}
