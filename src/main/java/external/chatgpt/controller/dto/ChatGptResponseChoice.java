package external.chatgpt.controller.dto;

public record ChatGptResponseChoice(
    int index,
    Message message,
    Object logprobs,
    String finish_reason) {

}