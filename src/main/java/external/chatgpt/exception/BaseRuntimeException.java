package external.chatgpt.exception;

import external.chatgpt.enums.Errors;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseRuntimeException extends RuntimeException{

  private static final Errors DEFAULT_ERROR = Errors.INTERNAL_SERVER_ERROR;
  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

  private final int errorCode;
  private final String errorMessage;
  private final int errorStatus;

  public BaseRuntimeException() {
    super();

    this.errorCode = DEFAULT_ERROR.getCode();
    this.errorMessage = DEFAULT_ERROR.getMessage();
    this.errorStatus = DEFAULT_HTTP_STATUS.value();
  }

  public BaseRuntimeException(Errors errors) {
    super(errors.getMessage());

    this.errorCode = errors.getCode();
    this.errorMessage = errors.getMessage();
    this.errorStatus = DEFAULT_HTTP_STATUS.value();
  }

}
