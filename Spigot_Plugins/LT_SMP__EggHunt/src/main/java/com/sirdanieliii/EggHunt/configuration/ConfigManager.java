package com.sirdanieliii.Operation_EggHunt.configuration;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.sirdanieliii.Operation_EggHunt.EggHunt.getInstance;

public class ConfigManager {
    protected File file;
    protected FileConfiguration config;
    // Set Variables
    public Double configVersion = Double.parseDouble(getInstance().getDescription().getVersion());
    public static String name = "LT SMP S3 Egg Hunt";
    public static String cmdHeader = "EggHunt";
    public static boolean grace = true;
    public static boolean timerCount = true;  // Count ticks in the game
    public static double timerPossession = 300.0d;  // 300 Seconds = 5 minutes
    public static int pointsKillMember = 1;
    public static int pointsPossession = 8;
    public static int pointsKillCarrier = 15;
    public static String eggPossession = "none";
    public static String eggLocationLast = "~ ~ ~";
    public static double eggLocationCheck = 60.0d; // 60 Seconds = 1 Minute
    public static int purpleTeamPoints;
    public static List<String> purpleTeamMembers;
    public static int yellowTeamPoints;
    public static List<String> yellowTeamMembers;
    public static List<String> nonTeamMembers;
    // Create list of headers
    private final String[] headers = {"config-version", "name", "header", "grace", "timer_count", "timer_possession", "points_kill_member", "points_possession", "points_kill_carrier",
            "egg_possession", "egg_location_last", "egg_location_check", "purple_team", "yellow_team", "no_team"};

    public void setup() {
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(
                getInstance().getName())).getDataFolder() + "/", "config.yml");
        // Create file if it doesn't exist
        config = YamlConfiguration.loadConfiguration(newFile(file));
        save();
        // Check Config Versions
        if (!Objects.equals(config.getDouble("config-version"), configVersion) && config.getDouble("config-version") != 0) {
            Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Older configuration file detected.");
            File old = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(
                    getInstance().getName())).getDataFolder() + "/", "config-old.yml");
            if (old.exists()) {
                try {
                    old.delete();
                    Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Deleting already existing config-old configuration file.");
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Could not delete already existing config-old configuration file.");
                }
            }
            if (file.renameTo(old)) {
                Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Successfully renamed old configuration file to \"config-old.yml\"");
                config = YamlConfiguration.loadConfiguration(newFile(file));
                Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Please import your settings into the new config file");
            } else Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Error renaming old configuration file");
        }
        for (String header : headers) {
            if (!config.contains(header)) {
                config.createSection(header);
                // Set Default Values
                switch (header) {
                    case ("config-version") -> config.set("config-version", configVersion);
                    case ("name") -> config.set("name", name);
                    case ("header") -> config.set("header", cmdHeader);
                    case ("grace") -> config.set("grace", grace);
                    case ("timer_count") -> config.set("timer_count", timerCount);
                    case ("timer_possession") -> config.set("timer_possession", timerPossession);
                    case ("points_kill_member") -> config.set("points_kill_member", pointsKillMember);
                    case ("points_possession") -> config.set("points_possession", pointsPossession);
                    case ("points_kill_carrier") -> config.set("points_kill_carrier", pointsKillCarrier);
                    case ("egg_possession") -> config.set("egg_possession", eggPossession);
                    case ("egg_location_last") -> {
                        config.set("egg_location_last.X", "~");
                        config.set("egg_location_last.Y", "~");
                        config.set("egg_location_last.Z", "~");
                    }
                    case ("egg_location_check") -> config.set("egg_location_check", eggLocationCheck);
                    case ("purple_team") -> {
                        config.set("purple_team.points", purpleTeamPoints);
                        config.set("purple_team.members", purpleTeamMembers);
                    }
                    case ("yellow_team") -> {
                        config.set("yellow_team.points", yellowTeamPoints);
                        config.set("yellow_team.members", yellowTeamMembers);
                    }
                    case ("no_team") -> config.set("no_team", nonTeamMembers);
                }
            }
        }
        save();
        reload();
    }

    public File newFile(File f) {
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Did not detect a config file. Generating a new one");
            }
        }
        return f;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("[" + cmdHeader + "] Could not save to config file");
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
        configVersion = config.getDouble("config-version");
        name = config.getString("name");
        cmdHeader = config.getString("header");
        grace = config.getBoolean("grace");
        timerCount = config.getBoolean("timer_count");
        timerPossession = config.getDouble("timer_possession");
        pointsKillMember = config.getInt("points_kill_member");
        pointsPossession = config.getInt("points_possession");
        pointsKillCarrier = config.getInt("points_kill_carrier");
        eggPossession = config.getString("egg_possession");
        eggLocationLast = config.getInt("egg_location_last.X") + " " + config.getInt("egg_location_last.Y") + " " + config.getInt("egg_location_last.Z");
        eggLocationCheck = config.getDouble("egg_location_check");
        purpleTeamPoints = config.getInt("purple_team.points");
        purpleTeamMembers = config.getStringList("purple_team.members");
        yellowTeamPoints = config.getInt("yellow_team.points");
        yellowTeamMembers = config.getStringList("points_possession");
        nonTeamMembers = config.getStringList("no_team");
    }
}
