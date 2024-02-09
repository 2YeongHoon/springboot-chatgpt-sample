package external.chatgpt.controller.dto;

public record ChatGptRequest(String model, Message[] messages, int temperature) {

}
