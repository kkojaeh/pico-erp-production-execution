package pico.erp.production.execution;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface ProductionExecutionExceptions {

  @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "production-execution.already.exists.exception")
  class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
  }

  @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "production-execution.cannot.update.exception")
  class CannotUpdateException extends RuntimeException {

    private static final long serialVersionUID = 1L;
  }


  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "production-execution.not.found.exception")
  class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

  }
}
