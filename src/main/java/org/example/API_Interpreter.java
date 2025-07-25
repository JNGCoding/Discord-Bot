package org.example;

import org.example.API_Interface.GeminiInterface;

public class API_Interpreter {
    GeminiInterface gemini;

    public API_Interpreter(Config config) {
        gemini = new GeminiInterface(config.get("GEMINI TOKEN"), config.get("GEMINI ANSWER LENGTH"));
    }

    public String QueryGemini(String query) {
        return gemini.Query(query);
    }
}
