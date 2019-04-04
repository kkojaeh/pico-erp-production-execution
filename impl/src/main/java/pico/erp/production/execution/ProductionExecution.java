package pico.erp.production.execution;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.val;
import pico.erp.item.ItemId;
import pico.erp.item.spec.ItemSpecCode;
import pico.erp.process.ProcessId;
import pico.erp.production.order.ProductionOrderId;
import pico.erp.shared.data.UnitKind;
import pico.erp.user.UserId;

/**
 * 주문 접수
 */
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductionExecution implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
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

  public ProductionExecution() {

  }

  public ProductionExecutionMessages.Create.Response apply(
    ProductionExecutionMessages.Create.Request request) {
    this.id = request.getId();
    this.orderId = request.getOrderId();
    this.itemId = request.getItemId();
    this.processId = request.getProcessId();
    this.itemSpecCode = request.getItemSpecCode();
    this.quantity = request.getQuantity();
    this.errorQuantity = request.getErrorQuantity();
    this.unit = request.getUnit();
    this.executorId = request.getExecutorId();
    this.startDate = request.getStartDate();
    this.endDate = request.getEndDate();
    this.executedDate = LocalDateTime.now();
    return new ProductionExecutionMessages.Create.Response(
      Arrays.asList(new ProductionExecutionEvents.CreatedEvent(this.id))
    );
  }

  public ProductionExecutionMessages.Update.Response apply(
    ProductionExecutionMessages.Update.Request request) {
    if (!isUpdatable()) {
      throw new ProductionExecutionExceptions.CannotUpdateException();
    }
    val previousQuantity = this.quantity;
    val previousErrorQuantity = this.errorQuantity;
    this.quantity = request.getQuantity();
    this.errorQuantity = request.getErrorQuantity();
    this.startDate = request.getStartDate();
    this.endDate = request.getEndDate();
    return new ProductionExecutionMessages.Update.Response(
      Arrays.asList(new ProductionExecutionEvents.UpdatedEvent(this.id, previousQuantity,
        previousErrorQuantity))
    );
  }

  public boolean isUpdatable() {
    if (executedDate == null) {
      return false;
    }
    return LocalDateTime.now().isBefore(executedDate.plusDays(1));
  }


}
