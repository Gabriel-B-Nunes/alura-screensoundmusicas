package br.com.alura.screensoundmusicas.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

    public static String pesquisaArtista(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .temperature(0.7)
                .maxTokens(100)
                .prompt("Quem é " + texto)
                .build();

        try {
            CompletionResult result = service.createCompletion(request);
            return result.getChoices().get(0).getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
