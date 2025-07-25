package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Config {
    public HashMap<String, String> tokens = new HashMap<>();

    public String get(String key) {
        return tokens.get(key);
    }

    public static void create(Config c, String Path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            while (reader.ready()) {
                String[] token = reader.readLine().split("=");
                c.tokens.put(token[0], token[1]);
            }
        }
    }
}
