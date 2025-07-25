package org.example.events;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UpdateListener extends ListenerAdapter {
    @Override
    public void onChannelCreate(@NotNull ChannelCreateEvent event) {
        super.onChannelCreate(event);
        TextChannel textChannel = event.getGuild().getTextChannelsByName("updates", true).get(0);
        if (textChannel == null) {
            System.out.println("Update Listener - onChannelCreate - textchannel was null.");
            return;
        }
        textChannel.sendMessage("God Dammit, Here comes a New channel named " + event.getChannel().getName()).queue();
    }

    @Override
    public void onChannelDelete(@NotNull ChannelDeleteEvent event) {
        super.onChannelDelete(event);
        TextChannel textChannel = event.getGuild().getTextChannelsByName("updates", true).get(0);
        if (textChannel == null) {
            System.out.println("Update Listener - onChannelDelete - textchannel was null.");
            return;
        }
        textChannel.sendMessage("Bye Bye my channel, " + event.getChannel().getName()).queue();
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        TextChannel textChannel = event.getGuild().getTextChannelsByName("updates", true).get(0);
        if (textChannel == null) {
            System.out.println("Update Listener - onMemberJoin - textchannel was null.");
            return;
        }
        textChannel.sendMessage("Welcome to our Server, " + event.getMember().getUser().getName()).queue();
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        super.onGuildMemberRemove(event);
        TextChannel textChannel = event.getGuild().getTextChannelsByName("updates", true).get(0);
        if (textChannel == null) {
            System.out.println("Update Listener - onMemberDelete - textchannel was null.");
            return;
        }
        textChannel.sendMessage("Bye Bye my member, " + Objects.requireNonNull(event.getMember()).getUser().getName()).queue();
    }
}
