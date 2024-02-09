package external.chatgpt.controller.dto;

import external.chatgpt.controller.dto.ChatGptResponseChoice;
import external.chatgpt.controller.dto.ChatGptResponseUsage;

public record ChatGptResponse(
    String id,
    String object,
    int created,
    String model,
    ChatGptResponseChoice[] choices,
    ChatGptResponseUsage usage
) {

}
