package pico.erp.production.execution;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.item.ItemId;
import pico.erp.item.spec.ItemSpecCode;
import pico.erp.process.ProcessId;
import pico.erp.production.order.ProductionOrderId;
import pico.erp.shared.data.UnitKind;
import pico.erp.user.UserId;

public interface ProductionExecutionRequests {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class CreateRequest {

    @Valid
    @NotNull
    ProductionExecutionId id;

    @Valid
    ProductionOrderId orderId;

    @Valid
    @NotNull
    ItemId itemId;

    @Valid
    @NotNull
    ProcessId processId;

    @Valid
    @NotNull
    ItemSpecCode itemSpecCode;

    @NotNull
    @Min(0)
    BigDecimal quantity;

    @NotNull
    @Min(0)
    BigDecimal errorQuantity;

    @NotNull
    UnitKind unit;

    @NotNull
    OffsetDateTime startDate;

    @NotNull
    OffsetDateTime endDate;

    @Valid
    @NotNull
    UserId executorId;


  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class UpdateRequest {

    @Valid
    @NotNull
    ProductionExecutionId id;

    @NotNull
    @Min(0)
    BigDecimal quantity;

    @NotNull
    @Min(0)
    BigDecimal errorQuantity;

    @NotNull
    OffsetDateTime startDate;

    @NotNull
    OffsetDateTime endDate;

  }

}
