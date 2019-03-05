package pico.erp.production.execution;

import java.util.Optional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface ProductionExecutionEntityRepository extends
  CrudRepository<ProductionExecutionEntity, ProductionExecutionId> {

}

@Repository
@Transactional
public class ProductionExecutionRepositoryJpa implements ProductionExecutionRepository {

  @Autowired
  private ProductionExecutionEntityRepository repository;

  @Autowired
  private ProductionExecutionMapper mapper;

  @Override
  public ProductionExecution create(ProductionExecution plan) {
    val entity = mapper.jpa(plan);
    val created = repository.save(entity);
    return mapper.jpa(created);
  }

  @Override
  public void deleteBy(ProductionExecutionId id) {
    repository.delete(id);
  }

  @Override
  public boolean exists(ProductionExecutionId id) {
    return repository.exists(id);
  }

  @Override
  public Optional<ProductionExecution> findBy(ProductionExecutionId id) {
    return Optional.ofNullable(repository.findOne(id))
      .map(mapper::jpa);
  }

  @Override
  public void update(ProductionExecution plan) {
    val entity = repository.findOne(plan.getId());
    mapper.pass(mapper.jpa(plan), entity);
    repository.save(entity);
  }
}
