package external.chatgpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import external.chatgpt.controller.dto.ChatGptRequest;
import external.chatgpt.controller.dto.ChatGptResponse;
import external.chatgpt.controller.dto.ChatGptResponseChoice;
import external.chatgpt.controller.dto.Message;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api")
public class ChatGptController {

  @PostMapping("")
  public ResponseEntity<String> chat(@RequestBody String prompt)
      throws IOException, InterruptedException {

    ObjectMapper mapper = new ObjectMapper();
    Message message = new Message("user", prompt);
    Message[] messages = {message};
    ChatGptRequest chatGptRequest = new ChatGptRequest("gpt-3.5-turbo", messages, 1);
    String input = mapper.writeValueAsString(chatGptRequest);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.openai.com/v1/chat/completions"))
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer key")
        .POST(HttpRequest.BodyPublishers.ofString(input))
        .build();

    HttpClient client = HttpClient.newHttpClient();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());

    ChatGptResponse chatGptResponse = mapper.readValue(response.body(), ChatGptResponse.class);
    ChatGptResponseChoice[] choices = chatGptResponse.choices();
    String answer = choices[0].message().content().trim();

    return ResponseEntity.ok(answer);
  }
}
