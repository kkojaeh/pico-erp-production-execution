package pico.erp.production.execution;

import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductionExecutionQuery {

  Page<ProductionExecutionView> retrieve(@NotNull ProductionExecutionView.Filter filter,
    @NotNull Pageable pageable);

}
