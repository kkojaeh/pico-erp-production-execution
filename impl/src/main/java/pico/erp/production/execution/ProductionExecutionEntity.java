package pico.erp.production.execution;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pico.erp.item.ItemId;
import pico.erp.item.spec.ItemSpecCode;
import pico.erp.process.ProcessId;
import pico.erp.production.order.ProductionOrderId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.UnitKind;
import pico.erp.user.UserId;

@Entity(name = "ProductionExecution")
@Table(name = "PDE_PRODUCTION_EXECUTION", indexes = {
  @Index(columnList = "ORDER_ID")
})
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionExecutionEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  ProductionExecutionId id;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ORDER_ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  ProductionOrderId orderId;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ITEM_ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  ItemId itemId;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "PROCESS_ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  ProcessId processId;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ITEM_SPEC_CODE", length = TypeDefinitions.CODE_LENGTH))
  })
  ItemSpecCode itemSpecCode;

  @Column(precision = 19, scale = 2)
  BigDecimal quantity;

  @Column(precision = 19, scale = 2)
  BigDecimal errorQuantity;

  @Column(length = TypeDefinitions.ENUM_LENGTH)
  @Enumerated(EnumType.STRING)
  UnitKind unit;

  OffsetDateTime startDate;

  OffsetDateTime endDate;

  OffsetDateTime executedDate;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "EXECUTOR_ID", length = TypeDefinitions.ID_LENGTH))
  })
  UserId executorId;

}
