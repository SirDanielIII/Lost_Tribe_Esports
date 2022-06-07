package com.sirdanieliii.EggHunt;

import com.sirdanieliii.EggHunt.configuration.ConfigManager;
import com.sirdanieliii.EggHunt.events.EggLocation;
import com.sirdanieliii.EggHunt.events.EggProtection;
import com.sirdanieliii.EggHunt.events.Events;
import com.sirdanieliii.EggHunt.events.Scoreboard;
import com.sirdanieliii.EggHunt.tasks.EverySecond;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public class LT_EggHunt extends JavaPlugin {
    public static ConfigManager CONFIG;
    private static LT_EggHunt instance;


    public LT_EggHunt() {
        instance = this;
    }

    public static LT_EggHunt getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Config Files
        CONFIG = new ConfigManager();
        CONFIG.setup();
        // Initialize Events
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new EggProtection(), this);
        getServer().getPluginManager().registerEvents(new EggLocation(), this);
        getServer().getPluginManager().registerEvents(new Scoreboard(), this);
        // Tasks
        BukkitTask everySecond = new EverySecond(this).runTaskTimer(this, 0L, 20L);  // Every second
        // Commands
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloading files...");
        this.getServer().getScheduler().cancelTasks(this);
    }
}
