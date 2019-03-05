package pico.erp.production.execution;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.shared.event.Event;

public interface ProductionExecutionEvents {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class CreatedEvent implements Event {

    public final static String CHANNEL = "event.production-execution.created";

    private ProductionExecutionId id;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class UpdatedEvent implements Event {

    public final static String CHANNEL = "event.production-execution.updated";

    private ProductionExecutionId id;

    private BigDecimal previousQuantity;

    private BigDecimal previousErrorQuantity;

    public String channel() {
      return CHANNEL;
    }

  }


}
