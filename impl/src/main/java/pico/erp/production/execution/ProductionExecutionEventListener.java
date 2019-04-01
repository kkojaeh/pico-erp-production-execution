package pico.erp.production.execution;

import kkojaeh.spring.boot.component.Take;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pico.erp.production.order.ProductionOrderRequests;
import pico.erp.production.order.ProductionOrderService;

@SuppressWarnings("unused")
@Component
public class ProductionExecutionEventListener {

  private static final String LISTENER_NAME = "listener.production-execution-event-listener";

  @Take
  private ProductionOrderService productionOrderService;

  @Autowired
  private ProductionExecutionService productionExecutionService;

  @EventListener
  @JmsListener(destination = LISTENER_NAME + "."
    + ProductionExecutionEvents.CreatedEvent.CHANNEL)
  public void onExecutionCreated(ProductionExecutionEvents.CreatedEvent event) {
    val execution = productionExecutionService.get(event.getId());
    if (execution.getOrderId() != null) {
      productionOrderService.progress(
        ProductionOrderRequests.ProgressRequest.builder()
          .id(execution.getOrderId())
          .progressedQuantity(execution.getQuantity())
          .erroredQuantity(execution.getErrorQuantity())
          .build()
      );
    }
  }

  @EventListener
  @JmsListener(destination = LISTENER_NAME + "."
    + ProductionExecutionEvents.UpdatedEvent.CHANNEL)
  public void onExecutionUpdated(ProductionExecutionEvents.UpdatedEvent event) {
    val execution = productionExecutionService.get(event.getId());
    if (execution.getOrderId() != null) {

      val quantity = execution.getQuantity().subtract(event.getPreviousQuantity());
      val errorQuantity = execution.getErrorQuantity().subtract(event.getPreviousErrorQuantity());
      productionOrderService.progress(
        ProductionOrderRequests.ProgressRequest.builder()
          .id(execution.getOrderId())
          .progressedQuantity(quantity)
          .erroredQuantity(errorQuantity)
          .build()
      );
    }
  }

}
