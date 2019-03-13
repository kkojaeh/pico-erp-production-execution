package pico.erp.production.execution;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.ApplicationId;
import pico.erp.shared.data.Role;

public final class ProductionExecutionApi {

  public final static ApplicationId ID = ApplicationId.from("production-execution");

  @RequiredArgsConstructor
  public enum Roles implements Role {

    PRODUCTION_EXECUTOR,
    PRODUCTION_EXECUTION_MANAGER;

    @Id
    @Getter
    private final String id = name();

  }
}
