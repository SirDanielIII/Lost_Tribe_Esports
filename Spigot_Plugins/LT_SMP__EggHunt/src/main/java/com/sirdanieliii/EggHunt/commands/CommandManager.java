package com.sirdanieliii.EggHunt.commands;

import com.sirdanieliii.EggHunt.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;


public class CommandManager implements TabExecutor {
    static Map<String, List<SubCommand>> subcommands = new HashMap<>();
    List<SubCommand> set = new ArrayList<>();

    public CommandManager() {
        set.add(new setTeam());
        subcommands.put("set", set);
    }

    public static ArrayList<SubCommand> getSubcommands(String key) {
        return (ArrayList<SubCommand>) subcommands.get(key);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return false;
        boolean present = false;
        if (args.length > 0) {
            try {
                for (int i = 0; i < getSubcommands(cmd.getName()).size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubcommands(cmd.getName()).get(i).getName())) {
                        getSubcommands(cmd.getName()).get(i).perform(p, args);
                        present = true;
                    }
                }
            } catch (Exception ignored) {
            }
            if (!present) incorrectFirstArg(p, label, args); // Spit out message if it fails to retrieve subcommand
        } else {
            displayCommands(p, label);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) { // /command <subcommand> <args>
            ArrayList<String> subcommandArgs = new ArrayList<>();
            for (int i = 0; i < getSubcommands(cmd.getName()).size(); i++) {
                subcommandArgs.add(getSubcommands(cmd.getName()).get(i).getName());
            }
            return subcommandArgs;
        } else if (args.length >= 2) {
            for (int i = 0; i < getSubcommands(cmd.getName()).size(); i++) {
                if (args[0].equalsIgnoreCase(getSubcommands(cmd.getName()).get(i).getName())) {
                    return getSubcommands(cmd.getName()).get(i).getSubcommandArgs((Player) sender, args);
                }
            }
        }
        return null;
    }

    public static String headerColour(String type, boolean bold) {
        if (bold) {
            return switch (type.toUpperCase()) {
                default -> null;
            };
        } else {
            return switch (type.toUpperCase()) {
                default -> null;
            };
        }
    }

    public void incorrectFirstArg(Player player, String cmd, String[] args) {
        switch (cmd.toLowerCase()) {
            default -> {
            }
        }
    }

    public static void displayCommands(Player player, String label) {
        String name = headerColour(label, true) + label.toUpperCase();
        player.sendMessage("------------ | " + name + "§R§F | ------------>");
        for (SubCommand i : subcommands.get(label)) {
            player.sendMessage(i.getSyntax() + " §7→ " + i.getDescription());
        }
        player.sendMessage("<-------------------------------------------------->");
    }

    public static void displayAllCommands(Player player) { // Maybe add pages to this one day
        // Make header a variable from config
        player.sendMessage("------------ | §6§L" + "Commands" + "§R§F | ------------>");
        for (List<SubCommand> i : subcommands.values()) {
            for (SubCommand subCommand : i) {
                player.sendMessage(subCommand.getSyntax() + " §7→ " + subCommand.getDescription());
            }
        }
        player.sendMessage("<-------------------------------------------------->");
    }
}