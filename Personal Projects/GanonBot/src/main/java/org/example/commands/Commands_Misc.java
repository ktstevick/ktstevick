package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class Commands_Misc extends ListenerAdapter {
    Map<String, String> textResource;

    public Commands_Misc(Map<String, String> textResource) {
        this.textResource = textResource;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        String response = "";

        event.deferReply().queue();

        // LISTS
        if(command.substring(0, 4).contains("list")) {
            switch (command) {
                case "list": response = textResource.get("list"); break;
                case "list-classic": response = textResource.get("list-classic"); break;
            }

            event.getHook().sendMessage(response).queue();
        }

        // INITIAL DEBUG
        if(command.substring(0, 4).contains("misc")) {
            switch (command) {
                case "misc-ping":
                    event.getHook().sendMessage("pong!").queue(); // .setEphemeral(true) sets visibility to user only
                    break;

                case "misc-food":
                    OptionMapping option = event.getOption("name");
                    if (option == null) {
                        event.getHook().sendMessage("No food name provided.");
                        return;
                    }

                    String favoriteFood = option.getAsString();
                    event.getHook().sendMessage("Your favorite food is - " + favoriteFood).queue();
                    break;

                case "misc-sum":
                    OptionMapping num1 = event.getOption("num1");
                    OptionMapping num2 = event.getOption("num2");
                    if (num1 == null || num2 == null) {
                        event.reply("No numbers??").queue();
                        return;
                    }

                    int sum = num1.getAsInt() + num2.getAsInt();
                    event.getHook().sendMessage("The sum is: " + sum).queue();
                    break;
            }
        }
    }
}
