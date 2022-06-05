package com.sirdanieliii.Operation_EggHunt.tasks;

import com.sirdanieliii.Operation_EggHunt.EggHunt;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import static com.sirdanieliii.Operation_EggHunt.configuration.ConfigManager.timerPossession;


public class EveryTick extends BukkitRunnable {
    EggHunt plugin;
    public static int ticks = 0;
    public static double secondsPassed = 0;
    public static double timeUntilPoints = timerPossession;

    public EveryTick(EggHunt plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (ticks >= 20 * timerPossession) {
            ticks = 0;
            Bukkit.broadcastMessage("5 MINUTES HAS PASSED");
        }
        ticks += 1;
        secondsPassed = Math.ceil(ticks / 20.0d);
        timeUntilPoints = timerPossession - secondsPassed;
    }
}