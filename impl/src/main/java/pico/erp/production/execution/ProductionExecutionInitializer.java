package pico.erp.production.execution;

import kkojaeh.spring.boot.component.ComponentAutowired;
import kkojaeh.spring.boot.component.SpringBootComponentReadyEvent;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import pico.erp.user.group.GroupRequests;
import pico.erp.user.group.GroupService;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class ProductionExecutionInitializer implements
  ApplicationListener<SpringBootComponentReadyEvent> {

  @ComponentAutowired
  private GroupService groupService;

  @Autowired
  private ProductionExecutionProperties properties;


  @Override
  public void onApplicationEvent(SpringBootComponentReadyEvent event) {
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
