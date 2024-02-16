package external.chatgpt.controller;

import external.chatgpt.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
public class ChatGptController {

  private final ChatGptService gptService;

  // TODO 반환값 String에서 응답객체로 수정
  @PostMapping("")
  public ResponseEntity<String> chat(@RequestBody String prompt) {
    return ResponseEntity.ok(gptService.getContents(prompt));
  }

}
