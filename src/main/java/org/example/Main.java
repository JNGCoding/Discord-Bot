package org.example;

import java.io.IOException;

import org.example.events.MessageListener;
import org.example.events.ReadyBot;
import org.example.events.SlashCommandListener;
import org.example.events.UpdateListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    private static Config config;
    private static API_Interpreter APIInterface;

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        config = new Config();
        Config.create(config, System.getProperty("user.dir") + "\\Assets\\ApplicationIDs.txt");

        APIInterface = new API_Interpreter(config);

        // Building the Bot
        JDABuilder jdaBuilder = JDABuilder.createDefault(config.get("TOKEN"));
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageListener(config, APIInterface), new ReadyBot(config), new SlashCommandListener(APIInterface), new UpdateListener())
                .build();
    }
}