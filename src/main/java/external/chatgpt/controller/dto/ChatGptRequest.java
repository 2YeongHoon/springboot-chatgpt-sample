package external.chatgpt.controller.dto;

public record ChatGptRequest(String model, String prompt, int temperature, int max_tokens) {

}
