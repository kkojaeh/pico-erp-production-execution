package pico.erp.production.execution;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.AuditorAware;
import pico.erp.item.ItemService;
import pico.erp.item.spec.ItemSpecService;
import pico.erp.shared.data.Auditor;

@Mapper
public abstract class ProductionExecutionMapper {

  @Autowired
  protected AuditorAware<Auditor> auditorAware;

  @Lazy
  @Autowired
  protected ItemService itemService;

  @Lazy
  @Autowired
  protected ItemSpecService itemSpecService;


  public abstract ProductionExecutionEntity jpa(ProductionExecution data);

  public ProductionExecution jpa(ProductionExecutionEntity entity) {
    return ProductionExecution.builder()
      .id(entity.getId())
      .orderId(entity.getOrderId())
      .itemId(entity.getItemId())
      .processId(entity.getProcessId())
      .itemSpecCode(entity.getItemSpecCode())
      .quantity(entity.getQuantity())
      .errorQuantity(entity.getErrorQuantity())
      .unit(entity.getUnit())
      .executorId(entity.getExecutorId())
      .startDate(entity.getStartDate())
      .endDate(entity.getEndDate())
      .executedDate(entity.getExecutedDate())
      .build();
  }


  @Mappings({
  })
  public abstract ProductionExecutionData map(ProductionExecution productionExecution);

  @Mappings({
  })
  public abstract ProductionExecutionMessages.Create.Request map(
    ProductionExecutionRequests.CreateRequest request);

  public abstract ProductionExecutionMessages.Update.Request map(
    ProductionExecutionRequests.UpdateRequest request);

  public abstract void pass(
    ProductionExecutionEntity from, @MappingTarget ProductionExecutionEntity to);


}


