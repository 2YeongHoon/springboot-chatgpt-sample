package external.chatgpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import external.chatgpt.controller.dto.ChatGptRequest;
import external.chatgpt.controller.dto.ChatGptResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
  public void chat(@RequestBody String prompt) throws IOException, InterruptedException {
//    String prompt;
//    if (args.length > 0) {
//      prompt = args[0];
//    } else {
//      Scanner scanner = new Scanner(System.in);
//      System.out.println("Enter a string to search for: ");
//      prompt = scanner.nextLine();
//    }

    ObjectMapper mapper = new ObjectMapper();
    ChatGptRequest chatGptRequest = new ChatGptRequest("gpt-3.5-turbo", prompt, 1, 100);
    String input = mapper.writeValueAsString(chatGptRequest);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.openai.com/v1/completions"))
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer key")
        .POST(HttpRequest.BodyPublishers.ofString(input))
        .build();

    HttpClient client = HttpClient.newHttpClient();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
      ChatGptResponse chatGptResponse = mapper.readValue(response.body(), ChatGptResponse.class);
      String answer = chatGptResponse.choices()[chatGptResponse.choices().length - 1].text();
      if (!answer.isEmpty()) {
        System.out.println(answer.replace("\n", "").trim());
      }
    } else {
      System.out.println(response.statusCode());
      System.out.println(response.body());
    }
  }
}
