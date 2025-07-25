package org.example.events;

import java.util.List;
import java.util.Objects;

import org.example.API_Interpreter;
import org.example.Config;
import org.example.LogDispensor;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

@SuppressWarnings("unused")
public class MessageListener extends ListenerAdapter {
    private final boolean testing;
    private final API_Interpreter API;

    private boolean searchFor(boolean ignoreCase, String... strings) {
        String searched = strings[0];
        if (!ignoreCase) {
            for (int i = 1; i < strings.length; i++) {
                if (!searched.contains(strings[i])) { return false; }
            }
        } else {
            for (int i = 1; i < strings.length; i++) {
                if (!searched.contains(strings[i].toLowerCase())) { return false; }
            }
        }
        return true;
    }

    public MessageListener(Config config, API_Interpreter interf) {
        API = interf;
        testing = config.get("TESTING").equals("TRUE");
    }

    @Override @SuppressWarnings("UnnecessaryReturnStatement")
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        super.onMessageReceived(e);

        String msg = e.getMessage().getContentDisplay();
        List<TextChannel> text_channels;
        TextChannel TC;

        Member self = e.getGuild().getSelfMember();
        GuildVoiceState selfVoicestate = self.getVoiceState();
        AudioManager audioManager = e.getGuild().getAudioManager();
        assert selfVoicestate != null;

        if (testing) {
            text_channels = e.getGuild().getTextChannelsByName("bot_testing", true);
            if (text_channels.isEmpty()) {
                LogDispensor.error("bot_testing text channel not found.");
                return;
            } else {
                TC = text_channels.get(0);
            }
        } else {
            TC = e.getMessage().getChannel().asTextChannel();
        }

        LogDispensor.event("Message Event.");
        LogDispensor.message(msg);
        if (msg.contains("botCommand/") && !Objects.requireNonNull(e.getMember()).getUser().getName().equals("YoruHater")) {
            String[] parts = msg.split(" ");
            String[] sub_parts = parts[0].split("/");
            String cmd = sub_parts[1];
            LogDispensor.event("Command Triggered : " + cmd);

            if (cmd.equals("ask")) {
                String query = msg.substring(15);
                String answer = API.QueryGemini(query);
                if (answer.length() >= 2000) {
                    answer = answer.substring(0, 1999);
                }
                TC.sendMessage(answer).queue();
            }

            if (cmd.equals("join")) {
                String channel_name = msg.substring(16);
                List<VoiceChannel> voice_channels = e.getJDA().getVoiceChannelsByName(channel_name, false);
                VoiceChannel VC;
                if (voice_channels.isEmpty()) {
                    TC.sendMessage("Error : Voice Channel not found.").queue();
                    return;
                } else {
                    System.out.println(channel_name + " found.");
                    VC = voice_channels.get(0);
                }

                audioManager.openAudioConnection(VC);
            }

            if (cmd.equals("play")) {
                String link = msg.substring(16);
                //audioManager.setSendingHandler(new AudioInputHandler());
            }

            if (cmd.equals("leave")) {
                if (selfVoicestate.inAudioChannel()) {
                    audioManager.closeAudioConnection();
                } else {
                    TC.sendMessage("Error : Not present in an audio channel.").queue();
                    return;
                }
            }
        }
    }
}
