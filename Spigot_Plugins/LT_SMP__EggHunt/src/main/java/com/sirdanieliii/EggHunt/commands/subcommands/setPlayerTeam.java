package com.sirdanieliii.Operation_EggHunt.commands.subcommands;

import com.sirdanieliii.Operation_EggHunt.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class setPlayerTeam extends SubCommand {
    @Override
    public String getName() {
        return "player team";
    }

    @Override
    public String getDescription() {
        return "Sets a player to a team";
    }

    @Override
    public String getSyntax() {
        return "/beitzah set <player> <team>";
    }

    @Override
    public boolean perform(Player player, String[] args) {
        return false;
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
