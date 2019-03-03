package pico.erp.production.execution;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ProductionExecutionService {

  ProductionExecutionData create(@Valid @NotNull ProductionExecutionRequests.CreateRequest request);

  boolean exists(@Valid @NotNull ProductionExecutionId id);

  ProductionExecutionData get(@Valid @NotNull ProductionExecutionId id);

  void update(@Valid @NotNull ProductionExecutionRequests.UpdateRequest request);

}
