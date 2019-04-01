package pico.erp.production.execution;

import kkojaeh.spring.boot.component.Give;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("Duplicates")
@Service
@Give
@Transactional
@Validated
public class ProductionExecutionServiceLogic implements ProductionExecutionService {

  @Autowired
  private ProductionExecutionRepository productionExecutionRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private ProductionExecutionMapper mapper;


  @Override
  public ProductionExecutionData create(ProductionExecutionRequests.CreateRequest request) {
    val purchaseRequest = new ProductionExecution();
    val response = purchaseRequest.apply(mapper.map(request));
    if (productionExecutionRepository.exists(purchaseRequest.getId())) {
      throw new ProductionExecutionExceptions.AlreadyExistsException();
    }
    val created = productionExecutionRepository.create(purchaseRequest);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public boolean exists(ProductionExecutionId id) {
    return productionExecutionRepository.exists(id);
  }

  @Override
  public ProductionExecutionData get(ProductionExecutionId id) {
    return productionExecutionRepository.findBy(id)
      .map(mapper::map)
      .orElseThrow(ProductionExecutionExceptions.NotFoundException::new);
  }

  @Override
  public void update(ProductionExecutionRequests.UpdateRequest request) {
    val purchaseRequest = productionExecutionRepository.findBy(request.getId())
      .orElseThrow(ProductionExecutionExceptions.NotFoundException::new);
    val response = purchaseRequest.apply(mapper.map(request));
    productionExecutionRepository.update(purchaseRequest);
    eventPublisher.publishEvents(response.getEvents());
  }
}
