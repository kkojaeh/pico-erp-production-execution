package pico.erp.production.execution;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import pico.erp.item.ItemId;
import pico.erp.item.spec.ItemSpecCode;
import pico.erp.process.ProcessId;
import pico.erp.production.order.ProductionOrderId;
import pico.erp.shared.data.UnitKind;
import pico.erp.user.UserId;

@Data
public class ProductionExecutionData {

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

  boolean updatable;

}
