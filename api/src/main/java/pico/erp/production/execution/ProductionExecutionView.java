package pico.erp.production.execution;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

@Data
public class ProductionExecutionView {

  ProductionExecutionId id;

  ProductionOrderId orderId;

  ItemId itemId;

  ProcessId processId;

  ItemSpecCode itemSpecCode;

  BigDecimal quantity;

  BigDecimal errorQuantity;

  UnitKind unit;

  UserId executorId;

  LocalDateTime startDate;

  LocalDateTime endDate;

  LocalDateTime executedDate;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Filter {

    ProductionOrderId orderId;

    UserId executorId;

    ItemId itemId;

    LocalDateTime startDate;

    LocalDateTime endDate;

  }

}
