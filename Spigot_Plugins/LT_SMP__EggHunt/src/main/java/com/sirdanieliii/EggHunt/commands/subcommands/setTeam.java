package com.sirdanieliii.EggHunt.commands.subcommands;

import com.sirdanieliii.EggHunt.commands.SubCommand;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

import static com.sirdanieliii.EggHunt.LT_EggHunt.CONFIG;
import static com.sirdanieliii.EggHunt.configuration.ConfigPlayer.addPlayer;
import static com.sirdanieliii.EggHunt.other.Messages.errorMessage;

public class setTeam extends SubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Sets a player to a team";
    }

    @Override
    public String getSyntax() {
        return "/egghunt set <team> <player>";
    }

    @Override
    public boolean perform(Player player, String[] args) {
        if (!player.isOp()) {
            player.sendMessage(errorMessage("OP_ONLY"));
            return false;
        }

        if (args.length == 2) {
            player.sendMessage(errorMessage(getSyntax()));
            return false;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null) {
            player.sendMessage(errorMessage("PLAYER"));
            return false;
        }
        if (StringUtils.equalsAnyIgnoreCase(args[1], "yellow", "purple")) {
            addPlayer(target, args[1].toLowerCase());
        } else if (args[1].equalsIgnoreCase("none")) {
        }
        return true;
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
