package com.sirdanieliii;

import com.sirdanieliii.commands.*;
import org.bukkit.plugin.java.JavaPlugin;


public class LTE_Dreidel extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("dreidel").setExecutor(new Dreidel(this));
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Putting Away Dreidel...");
        this.getServer().getScheduler().cancelTasks(this);
    }
}
