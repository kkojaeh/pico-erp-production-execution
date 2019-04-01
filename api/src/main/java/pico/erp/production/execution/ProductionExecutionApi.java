package pico.erp.production.execution;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.data.Role;

public final class ProductionExecutionApi {

  @RequiredArgsConstructor
  public enum Roles implements Role {

    PRODUCTION_EXECUTOR,

    PRODUCTION_EXECUTION_MANAGER;

    @Id
    @Getter
    private final String id = name();

  }
}
