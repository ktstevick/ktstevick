package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Commands_Classic extends ListenerAdapter {

    Map<String, String> textResource;

    public Commands_Classic(Map<String, String> textResource) {
        this.textResource = textResource;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        event.deferReply().queue();

        String response = "'" + command + "' is not a valid command."; // Default

        switch (command) {
            case "jab": response = textResource.get("jab"); break;
            case "ftilt": response = textResource.get("ftilt"); break;
            case "dtilt": response = textResource.get("dtilt"); break;
            case "utilt": response = textResource.get("utilt"); break;
            case "dashattack": response = textResource.get("dashattack"); break;

            case "nair": response = textResource.get("nair"); break;
            case "fair": response = textResource.get("fair"); break;
            case "bair": response = textResource.get("bair"); break;
            case "dair": response = textResource.get("dair"); break;
            case "uair": response = textResource.get("uair"); break;

            case "neutralb": response = textResource.get("neutralb"); break;
            case "sideb": response = textResource.get("sideb"); break;
            case "upb": response = textResource.get("upb"); break;
            case "downb": response = textResource.get("downb"); break;

            case "info": response = textResource.get("info"); break;

            case "fsmash": response = textResource.get("fsmash"); break;
            case "dsmash": response = textResource.get("dsmash"); break;
            case "upsmash": response = textResource.get("upsmash"); break;

            case "grab": response = textResource.get("grab"); break;
            case "dashgrab": response = textResource.get("dashgrab"); break;
            case "roll": response = textResource.get("roll"); break;
            case "spotdodge": response = textResource.get("spotdodge"); break;
            case "airdodge": response = textResource.get("airdodge"); break;

            case "floorattack": response = textResource.get("floorattack"); break;
            case "ledgestand": response = textResource.get("ledgestand"); break;
            case "ledgeattack": response = textResource.get("ledgeattack"); break;
            case "ledgejump": response = textResource.get("ledgejump"); break;
            case "ledgeroll": response = textResource.get("ledgeroll"); break;
        }

        event.getHook().sendMessage(response).queue();
    }
}