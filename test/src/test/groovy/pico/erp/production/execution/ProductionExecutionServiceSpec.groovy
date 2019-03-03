package pico.erp.production.execution

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.item.ItemId
import pico.erp.item.spec.ItemSpecCode
import pico.erp.process.ProcessId
import pico.erp.shared.IntegrationConfiguration
import pico.erp.shared.data.UnitKind
import pico.erp.user.UserId
import spock.lang.Specification

import java.time.OffsetDateTime

@SpringBootTest(classes = [IntegrationConfiguration])
@Transactional
@Rollback
@ActiveProfiles("test")
@Configuration
@ComponentScan("pico.erp.config")
class ProductionExecutionServiceSpec extends Specification {

  @Autowired
  ProductionExecutionService requestService

  def id = ProductionExecutionId.from("execution-1")

  def unknownId = ProductionExecutionId.from("unknown")

  def processId = ProcessId.from("toothbrush-051")

  def executorId = UserId.from("kjh")

  def itemId = ItemId.from("toothbrush-05")

  def itemSpecCode = ItemSpecCode.NOT_APPLICABLE

  def quantity = 100

  def errorQuantity = 100

  def unit = UnitKind.EA

  def startDate = OffsetDateTime.now().minusHours(3)

  def endDate = OffsetDateTime.now()

  def setup() {
    requestService.create(
      new ProductionExecutionRequests.CreateRequest(
        id: id,
        itemId: itemId,
        itemSpecCode: itemSpecCode,
        quantity: quantity,
        errorQuantity: errorQuantity,
        unit: unit,
        processId: processId,
        startDate: startDate,
        endDate: endDate,
        executorId: executorId
      )
    )
  }


  def updateRequest() {
    requestService.update(
      new ProductionExecutionRequests.UpdateRequest(
        id: id,
        quantity: quantity,
        errorQuantity: errorQuantity,
        startDate: startDate,
        endDate: endDate
      )
    )
  }


  def "존재 - 아이디로 존재 확인"() {
    when:
    def exists = requestService.exists(id)

    then:
    exists == true
  }

  def "존재 - 존재하지 않는 아이디로 확인"() {
    when:
    def exists = requestService.exists(unknownId)

    then:
    exists == false
  }

  def "조회 - 아이디로 조회"() {
    when:
    def request = requestService.get(id)

    then:
    request.id == id
    request.itemId == itemId
    request.itemSpecCode == itemSpecCode
    request.quantity == quantity
    request.errorQuantity == errorQuantity
    request.unit == unit
    request.processId == processId
    request.executorId == executorId
    request.startDate == startDate
    request.endDate == endDate
  }

  def "조회 - 존재하지 않는 아이디로 조회"() {
    when:
    requestService.get(unknownId)

    then:
    thrown(ProductionExecutionExceptions.NotFoundException)
  }


  def "수정"() {
    when:
    updateRequest()
    def request = requestService.get(id)

    then:
    request.id == id
    request.itemId == itemId
    request.itemSpecCode == itemSpecCode
    request.quantity == quantity
    request.unit == unit
    request.errorQuantity == errorQuantity
    request.startDate == startDate
    request.endDate == endDate
  }


}
