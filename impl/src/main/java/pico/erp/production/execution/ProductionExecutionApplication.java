package pico.erp.production.execution;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import pico.erp.audit.AuditApi;
import pico.erp.audit.AuditConfiguration;
import pico.erp.item.ItemApi;
import pico.erp.process.ProcessApi;
import pico.erp.process.ProcessId;
import pico.erp.production.execution.ProductionExecutionApi.Roles;
import pico.erp.production.order.ProductionOrderApi;
import pico.erp.production.order.ProductionOrderId;
import pico.erp.project.ProjectApi;
import pico.erp.shared.ApplicationId;
import pico.erp.shared.ApplicationStarter;
import pico.erp.shared.Public;
import pico.erp.shared.SpringBootConfigs;
import pico.erp.shared.data.Role;
import pico.erp.shared.impl.ApplicationImpl;
import pico.erp.user.UserApi;
import pico.erp.warehouse.WarehouseApi;

@Slf4j
@SpringBootConfigs
public class ProductionExecutionApplication implements ApplicationStarter {

  public static final String CONFIG_NAME = "production-execution/application";

  public static final Properties DEFAULT_PROPERTIES = new Properties();

  static {
    DEFAULT_PROPERTIES.put("spring.config.name", CONFIG_NAME);
  }

  public static SpringApplication application() {
    return new SpringApplicationBuilder(ProductionExecutionApplication.class)
      .properties(DEFAULT_PROPERTIES)
      .web(false)
      .build();
  }

  public static void main(String[] args) {
    application().run(args);
  }

  @Override
  public Set<ApplicationId> getDependencies() {
    return Stream.of(
      UserApi.ID,
      ItemApi.ID,
      ProcessApi.ID,
      ProductionOrderApi.ID
    ).collect(Collectors.toSet());
  }

  @Override
  public ApplicationId getId() {
    return ProductionExecutionApi.ID;
  }

  @Override
  public boolean isWeb() {
    return false;
  }

  @Bean
  @Public
  public Role productionExecutor() {
    return Roles.PRODUCTION_EXECUTOR;
  }

  @Bean
  @Public
  public Role productionExecuteManager() {
    return Roles.PRODUCTION_EXECUTE_MANAGER;
  }

  @Override
  public pico.erp.shared.Application start(String... args) {
    return new ApplicationImpl(application().run(args));
  }

}
