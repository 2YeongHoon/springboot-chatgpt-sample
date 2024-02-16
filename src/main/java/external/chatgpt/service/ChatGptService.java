package external.chatgpt.service;

import external.chatgpt.client.ChatGptClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatGptService {

  private final ChatGptClient gptClient;

  public String getContents(String prompt) {
    return gptClient.send(prompt);
  }
}
