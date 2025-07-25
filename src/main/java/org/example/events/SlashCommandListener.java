package org.example.events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.API_Interpreter;
import org.example.LogDispensor;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener extends ListenerAdapter {
    private final API_Interpreter API;
    private final List<CommandData> commands;

    public SlashCommandListener(API_Interpreter interf) {
        API = interf;
        commands = new ArrayList<>();

        commands.add(Commands.slash("introduction", "Tell me about yourself."));
        commands.add(Commands.slash("name", "Reason behind the name."));
        commands.add(Commands.slash("joke", "Tell me a joke"));
        commands.add(Commands.slash("help", "List all the commands."));
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        LogDispensor.event("Slash Command Called - " + event.getName());
        switch (event.getName()) {
            case "introduction" -> event.reply("Hello User, I am YoruHater, a bot created by my creator, 'NormalBandhA' a.k.a Dhruv. I was created on 16/04/2025 and it is always a pleasure to serve you.").queue();

            case "name" -> event.reply("Hey, you might be wondering that why my name is Yoru Hater. The thing is IDK, In reality my creator hates Yoru Agent.").queue();

            case "joke" -> event.reply(API.QueryGemini("Tell me a short joke")).queue();

            case "help" -> event.reply(
                """
                Special Commands ["botCommand/" prefix commands] :
                1) ask {Question} --- Query an question to Google's Gemini API from your discord.
                
                Slash commands :
                1) introduction - a small introduction from the bot
                2) name - silly reason behind the name
                3) joke - to tell an joke
                4) help - to list all public commands
                """).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        super.onGuildReady(event);
        event.getGuild().updateCommands().addCommands(commands).queue();
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);
        event.getGuild().updateCommands().addCommands(commands).queue();
    }
}
