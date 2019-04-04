package pico.erp.production.execution;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kkojaeh.spring.boot.component.ComponentBean;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.production.execution.ProductionExecutionView.Filter;
import pico.erp.shared.jpa.QueryDslJpaSupport;

@Service
@ComponentBean
@Transactional(readOnly = true)
@Validated
public class ProductionExecutionQueryJpa implements ProductionExecutionQuery {


  private final QProductionExecutionEntity execution = QProductionExecutionEntity.productionExecutionEntity;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private QueryDslJpaSupport queryDslJpaSupport;

  @Override
  public Page<ProductionExecutionView> retrieve(Filter filter, Pageable pageable) {
    val query = new JPAQuery<ProductionExecutionView>(entityManager);
    val select = Projections.bean(ProductionExecutionView.class,
      execution.id,
      execution.orderId,
      execution.itemId,
      execution.processId,
      execution.itemSpecCode,
      execution.quantity,
      execution.errorQuantity,
      execution.unit,
      execution.executorId,
      execution.startDate,
      execution.endDate,
      execution.executedDate
    );
    query.select(select);
    query.from(execution);

    val builder = new BooleanBuilder();

    if (filter.getOrderId() != null) {
      builder.and(execution.orderId.eq(filter.getOrderId()));
    }

    if (filter.getExecutorId() != null) {
      builder.and(execution.executorId.eq(filter.getExecutorId()));
    }

    if (filter.getItemId() != null) {
      builder.and(execution.itemId.eq(filter.getItemId()));
    }

    if (filter.getStartDate() != null) {
      builder.and(execution.startDate.goe(filter.getStartDate()));
    }
    if (filter.getEndDate() != null) {
      builder.and(execution.endDate.loe(filter.getEndDate()));
    }

    query.where(builder);
    return queryDslJpaSupport.paging(query, pageable, select);
  }

}
