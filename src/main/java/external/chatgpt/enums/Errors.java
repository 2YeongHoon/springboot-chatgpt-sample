package external.chatgpt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {
  FAIL_TO_CONVERT_MESSAGE(1001, "프롬포트 변환 중 오류가 발생했습니다."),

  EXTERNAL_API_ERROR(1, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다."),
  INTERNAL_SERVER_ERROR(9999, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다."),
  ;

  private final int code;
  private final String message;
}
