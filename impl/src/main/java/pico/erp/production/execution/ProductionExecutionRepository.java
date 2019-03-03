package pico.erp.production.execution;

import java.time.OffsetDateTime;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionExecutionRepository {

  ProductionExecution create(@NotNull ProductionExecution orderAcceptance);

  void deleteBy(@NotNull ProductionExecutionId id);

  boolean exists(@NotNull ProductionExecutionId id);

  Optional<ProductionExecution> findBy(@NotNull ProductionExecutionId id);

  void update(@NotNull ProductionExecution orderAcceptance);

}
