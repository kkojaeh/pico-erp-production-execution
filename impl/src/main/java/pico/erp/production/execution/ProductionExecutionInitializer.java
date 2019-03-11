package pico.erp.production.execution;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pico.erp.shared.ApplicationInitializer;
import pico.erp.user.group.GroupRequests;
import pico.erp.user.group.GroupService;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class ProductionExecutionInitializer implements ApplicationInitializer {

  @Lazy
  @Autowired
  GroupService groupService;

  @Autowired
  ProductionExecutionProperties properties;

  @Override
  public void initialize() {
    val executorGroup = properties.getExecutorGroup();
    if (!groupService.exists(executorGroup.getId())) {
      groupService.create(
        GroupRequests.CreateRequest.builder()
          .id(executorGroup.getId())
          .name(executorGroup.getName())
          .build()
      );
    }
  }
}
