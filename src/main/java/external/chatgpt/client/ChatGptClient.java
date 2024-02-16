package external.chatgpt.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import external.chatgpt.controller.dto.ChatGptRequest;
import external.chatgpt.controller.dto.ChatGptResponse;
import external.chatgpt.controller.dto.ChatGptResponseChoice;
import external.chatgpt.controller.dto.Message;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatGptClient {

  // TODO 클라이언트 프로퍼티 별도 분리
  @Value("${chat-gpt.url}")
  private String API_URL;

  @Value("${chat-gpt.key}")
  private String API_KEY;

  @Value("${chat-gpt.role}")
  private String API_ROLE;

  @Value("${chat-gpt.model}")
  private String API_MODEL;

  public String send(String prompt) {

    try {
      ObjectMapper mapper = new ObjectMapper();
      Message message = new Message(API_ROLE, prompt);
      Message[] messages = {message};
      ChatGptRequest chatGptRequest = new ChatGptRequest(API_MODEL, messages, 1);
      String input = mapper.writeValueAsString(chatGptRequest);

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(API_URL))
          .header("Content-Type", "application/json")
          .header("Authorization", API_KEY)
          .POST(HttpRequest.BodyPublishers.ofString(input))
          .build();

      HttpClient client = HttpClient.newHttpClient();

      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      ChatGptResponse chatGptResponse = mapper.readValue(response.body(), ChatGptResponse.class);

      ChatGptResponseChoice[] choices = chatGptResponse.choices();
      return choices[0].message().content().trim();

    } catch (Exception e) {
      // TODO 오류 메시지 분리
      throw new RuntimeException("chatgpt 서비스 사용 중 오류");
    }
  }

}
