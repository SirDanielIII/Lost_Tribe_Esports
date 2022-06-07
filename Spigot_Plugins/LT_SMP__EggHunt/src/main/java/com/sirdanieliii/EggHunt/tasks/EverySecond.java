package com.sirdanieliii.EggHunt.tasks;

import com.sirdanieliii.EggHunt.LT_EggHunt;
import org.bukkit.scheduler.BukkitRunnable;

import static com.sirdanieliii.EggHunt.configuration.ConfigManager.timerPossession;


public class EverySecond extends BukkitRunnable {
    LT_EggHunt plugin;
    public static int seconds = 0;
    public static int timeUntilPoints = timerPossession;

    public EverySecond(LT_EggHunt plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (seconds >= timerPossession) {
            seconds = 0;
        }
        seconds += 1;
        timeUntilPoints = timerPossession - seconds;
    }
}