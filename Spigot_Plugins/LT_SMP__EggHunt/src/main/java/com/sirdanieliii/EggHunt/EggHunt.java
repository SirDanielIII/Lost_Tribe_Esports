package com.sirdanieliii.Operation_EggHunt;

import com.sirdanieliii.Operation_EggHunt.configuration.ConfigManager;
import com.sirdanieliii.Operation_EggHunt.events.Events;
import com.sirdanieliii.Operation_EggHunt.tasks.EveryTick;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public class EggHunt extends JavaPlugin {
    public static ConfigManager CONFIG;
    private static EggHunt instance;


    public EggHunt() {
        instance = this;
    }

    public static EggHunt getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Config Files
        CONFIG = new ConfigManager();
        CONFIG.setup();
        // Initialize Events
        getServer().getPluginManager().registerEvents(new Events(), this);
        // Tasks
        BukkitTask everyTick = new EveryTick(this).runTaskTimer(this, 0L, 1L);  // Every Tick
        // Commands
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloading files...");
        this.getServer().getScheduler().cancelTasks(this);
    }
}
