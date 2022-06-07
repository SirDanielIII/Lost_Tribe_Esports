package com.sirdanieliii.EggHunt.other;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

import java.util.Objects;
import java.util.UUID;


import static com.sirdanieliii.EggHunt.configuration.ConfigManager.purpleTeamMembers;
import static com.sirdanieliii.EggHunt.configuration.ConfigManager.yellowTeamMembers;

public class Teams {
    public static UUID carrier;
    public static Team purple;
    public static Team yellow;

    public static void initializeTeams() {
        for (String uuid : purpleTeamMembers)
            purple.addEntry(Objects.requireNonNull(Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(uuid))).getDisplayName()));
        for (String uuid : yellowTeamMembers)
            purple.addEntry(Objects.requireNonNull(Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(uuid))).getDisplayName()));

    }
}
