package com.sirdanieliii.commands;

import com.sirdanieliii.LTE_Dreidel;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Objects;
import java.util.Random;

public class Dreidel implements CommandExecutor {

    public LTE_Dreidel plugin;

    public Dreidel(LTE_Dreidel instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        Random rand = new Random();
        int random_int = rand.nextInt(4);
        String[] symbolsHebrew = {"נ", "ג", "ה", "ש"};
        String[] symbolsWords = {"Nun", "Gimel", "Hey", "Shin"};
        Color[] coloursClass = {Color.AQUA, Color.GREEN, Color.RED, Color.FUCHSIA};
        String[] coloursString = {"§B", "§A", "§C", "§D"};

        Bukkit.broadcastMessage("§6[§FDreidel§6] §BSpinning the Dreidel...");
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                Bukkit.broadcastMessage(
                        "§6[§FDreidel§6] §F" + player.getDisplayName() + " got " + coloursString[random_int] + symbolsHebrew[random_int] + " (" + symbolsWords[random_int] + ")");
                spawnFireworks(player.getLocation(), 1, coloursClass[random_int]);
            }
        }, 10); // 20 Ticks In 1 Second
        return true;
    }

    public static void spawnFireworks(Location location, int amount, Color colour) {
        Firework fw = (Firework) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(colour).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for (int i = 0; i < amount; i++) {
            Firework fw2 = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }
}
