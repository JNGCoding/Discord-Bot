package org.example.events;

import org.example.Config;
import org.example.LogDispensor;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyBot implements EventListener {
    private final boolean testing;
    public ReadyBot(Config config) {
        testing = config.get("TESTING").equals("TRUE");
    }

    @Override
    public void onEvent(@NotNull GenericEvent event){
        if (event instanceof ReadyEvent) {
            LogDispensor.pass("Bot up and Running.");
            if (testing) {
                LogDispensor.warning("Bot started in Test mode.");
            }
        }
    }
}