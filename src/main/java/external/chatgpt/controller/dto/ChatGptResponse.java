package external.chatgpt.controller.dto;

public record ChatGptResponse(
    String id,
    String object,
    int created,
    String model,
    ChatGptResponseChoice[] choices,
    ChatGptResponseUsage usage,
    String system_fingerprint
) {

}
