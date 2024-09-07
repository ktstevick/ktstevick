package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.commands.Commands_Classic;
import org.example.commands.Commands_Misc;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome, world!");

        // Map for reference
        Map<String, String> textResource = new HashMap<>();

        File commandFile = new File("src/main/resources/commands.txt");
        File configFile = new File("src/main/resources/config.txt");

        Scanner dataInput;

        String TOKEN = "";
        String GUILD_ID = "";

        String currentStr = "";

        try {
            dataInput = new Scanner(configFile);

            while (dataInput.hasNextLine()) {
                currentStr = dataInput.nextLine();

                System.out.println(currentStr);

                if(currentStr.contains("BOT_TOKEN")) {
                    TOKEN = currentStr.substring(11); // As of 9-4-24
                }

                if(currentStr.contains("SERVER_ID")) {
                    GUILD_ID = currentStr.substring(11); // Forsaken Fortress II ID
                }
            }

        } catch(FileNotFoundException e) {
            System.out.println("Something happened!");
        }

        String CMD_HEAD_DELIMITER = "~~~";
        String CMD_FOOT_DELIMITER = ",,,";

        String keyStr = "";
        String valueStr = "";
        String buildingStr = "";

        try {
            dataInput = new Scanner(commandFile);

            while(dataInput.hasNextLine()) {
                currentStr = dataInput.nextLine();

                if(currentStr.contains(CMD_HEAD_DELIMITER)) {
                    keyStr = currentStr.substring(CMD_HEAD_DELIMITER.length(), currentStr.length() - CMD_HEAD_DELIMITER.length());

                } else if (currentStr.contains(CMD_FOOT_DELIMITER)) {
                    valueStr = buildingStr;

                    textResource.put(keyStr, valueStr);

                    keyStr = "";
                    buildingStr = "";

                } else {
                    buildingStr += ("\r\n" + currentStr);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something else happened!");
        }

        // Actual JDA
        JDA jda = JDABuilder.createDefault(TOKEN)
                .setActivity(Activity.playing("Minecraft"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)

                .addEventListeners(new Commands_Misc(textResource))
                .addEventListeners(new Commands_Classic(textResource))

                .build()
                .awaitReady();

        Guild guild = jda.getGuildById(GUILD_ID);
        if(guild != null) {
            // CLASSIC COMMANDS, no prefix
            guild.upsertCommand("pong", "Test test").queue();

            guild.upsertCommand("jab", "Thunder Punch").queue();
            guild.upsertCommand("ftilt", "Armor Crush").queue();
            guild.upsertCommand("dtilt", "Sweeping Snake").queue();
            guild.upsertCommand("utilt", "Volcano Kick").queue();
            guild.upsertCommand("dashattack", "Iron Shoulder").queue();

            guild.upsertCommand("nair", "Swooping Keese").queue();
            guild.upsertCommand("fair", "The SkullCrusher").queue();
            guild.upsertCommand("bair", "Hidden Gauntlet").queue();
            guild.upsertCommand("dair", "Thunder Drop").queue();
            guild.upsertCommand("uair", "Vulture Kick").queue();

            guild.upsertCommand("neutralb", "Warlock Punch").queue();
            guild.upsertCommand("sideb", "Gerudo Dragon").queue();
            guild.upsertCommand("upb", "Dark Dive").queue();
            guild.upsertCommand("downb", "The Wizkick").queue();

            guild.upsertCommand("info", "Stats and Data").queue();

            guild.upsertCommand("fsmash", "Nightmare Lunge").queue();
            guild.upsertCommand("dsmash", "Leg Whip").queue();
            guild.upsertCommand("upsmash", "Tornado Kick").queue();

            guild.upsertCommand("grab", "Z").queue();
            guild.upsertCommand("dashgrab", "-> Z").queue();
            guild.upsertCommand("roll", "L or R ->").queue();
            guild.upsertCommand("spotdodge", "L or R down").queue();
            guild.upsertCommand("airdodge", "L or R midair").queue();

            guild.upsertCommand("floorattack", "In Da Club").queue();
            guild.upsertCommand("ledgestand", "Die Standing").queue();
            guild.upsertCommand("ledgeattack", "Best Foot Forward").queue();
            guild.upsertCommand("ledgejump", "The Tournament Winner").queue();
            guild.upsertCommand("ledgeroll", "Gettin' Dizzy With It").queue();

            // ADVANCED COMMANDS

            // MATCH UP COMMANDS

            // ABSTRACT COMMANDS

            // DEBUG AND MISC.
            guild.upsertCommand("misc-ping", "The Classic You All Know and Love").queue();
            guild.upsertCommand("misc-food", "We Need Food!")
                    .addOption(OptionType.STRING, "name", "Name of Food", true)
                    .queue();
            guild.upsertCommand("misc-sum", "Add two numbers")
                    .addOption(OptionType.INTEGER, "num1", "first number", true)
                    .addOption(OptionType.INTEGER, "num2", "second number", true)
                    .queue();
        }
    }
}