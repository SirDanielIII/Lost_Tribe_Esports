package com.sirdanieliii.EggHunt.configuration;

import org.bukkit.entity.Player;

import static com.sirdanieliii.EggHunt.LT_EggHunt.CONFIG;

public class ConfigPlayer {
    public static void addPlayer(Player player, String team) {
        String prefix = null;
        if (team.equalsIgnoreCase("purple")) prefix = "purple_team.members.";
        else if (team.equalsIgnoreCase("yellow")) prefix = "yellow_team.members.";

        String[] headers = {"name", "murders", "death_by_player", "death_by_other", "carrier_kills", "time_held_egg"};
        if (prefix != null) {
            String path = prefix + player.getUniqueId() + ".";
            for (String header : headers) {
                if (!CONFIG.getConfig().contains(header)) {
                    CONFIG.getConfig().createSection(header);
                    // Set Default Values
                    switch (header) {
                        case ("name") -> CONFIG.getConfig().set(path + "name", player.getDisplayName());
                        case ("murders") -> CONFIG.getConfig().set(path + "murders", 0.0D);
                        case ("death_by_player") -> CONFIG.getConfig().set(path + "death_by_player", 0.0D);
                        case ("death_by_other") -> CONFIG.getConfig().set(path + "death_by_other", 0.0D);
                        case ("carrier_kills") -> CONFIG.getConfig().set(path + "carrier_kills", 0.0D);
                        case ("time_held_egg") -> CONFIG.getConfig().set(path + "time_held_egg", 0.0D);
                    }
                }
            }
//            // Update Player Name
//            if (!player.getName().equalsIgnoreCase(String.valueOf(PLAYER_CONFIG.getConfig().contains("name")))) {
//                PLAYER_CONFIG.getConfig().set("name", player.getName());
//            }
            CONFIG.save();
        }
    }
}
