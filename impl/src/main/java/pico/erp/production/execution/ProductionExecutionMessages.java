package pico.erp.production.execution;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;
import pico.erp.item.ItemId;
import pico.erp.item.spec.ItemSpecCode;
import pico.erp.process.ProcessId;
import pico.erp.production.order.ProductionOrderId;
import pico.erp.shared.data.UnitKind;
import pico.erp.shared.event.Event;
import pico.erp.user.UserId;

public interface ProductionExecutionMessages {

  interface Create {

    @Data
    class Request {

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
      UserId executorId;

      @NotNull
      OffsetDateTime startDate;

      @NotNull
      OffsetDateTime endDate;

    }

    @Value
    class Response {

      Collection<Event> events;

    }
  }


  interface Update {

    @Data
    class Request {

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

    @Value
    class Response {

      Collection<Event> events;

    }
  }


}
