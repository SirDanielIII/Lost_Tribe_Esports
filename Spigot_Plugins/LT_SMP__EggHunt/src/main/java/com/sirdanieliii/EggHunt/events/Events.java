package com.sirdanieliii.Operation_EggHunt.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import static com.sirdanieliii.Operation_EggHunt.tasks.EveryTick.ticks;

import static com.sirdanieliii.Operation_EggHunt.EggHunt.getInstance;

import java.util.Objects;

public class Events implements Listener {

    @EventHandler
    public void joinServer(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("title", "dummy", "   §3§LLT SMP S3 - §6§LEGG HUNT   ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team l12 = scoreboard.registerNewTeam("l12");
        Team l11 = scoreboard.registerNewTeam("l11");
        Team l10 = scoreboard.registerNewTeam("l10");
        Team l9 = scoreboard.registerNewTeam("l9");
        Team l8 = scoreboard.registerNewTeam("l8");
        Team l7 = scoreboard.registerNewTeam("l7");
        Team l6 = scoreboard.registerNewTeam("l6");
        Team l5 = scoreboard.registerNewTeam("l5");
        Team l4 = scoreboard.registerNewTeam("l4");
        Team l3 = scoreboard.registerNewTeam("l3");
        Team l2 = scoreboard.registerNewTeam("l2");
        Team l1 = scoreboard.registerNewTeam("l1");

        l12.addEntry("§12");
        l11.addEntry("§11");
        l10.addEntry("§10");
        l9.addEntry("§9");
        l8.addEntry("§8");
        l7.addEntry("§7");
        l6.addEntry("§6");
        l5.addEntry("§5");
        l4.addEntry("§4");
        l3.addEntry("§3");
        l2.addEntry("§2");
        l1.addEntry("§1");
        l12.setPrefix("");
        l11.setPrefix("");
        l10.setPrefix("");
        l9.setPrefix("");
        l8.setPrefix("");
        l7.setPrefix("");
        l6.setPrefix("");
        l5.setPrefix("");
        l4.setPrefix("");
        l3.setPrefix("");
        l2.setPrefix("");
        l1.setPrefix("");

        obj.getScore("§12").setScore(12);
        obj.getScore("§11").setScore(11);
        obj.getScore("§10").setScore(10);
        obj.getScore("§9").setScore(9);
        obj.getScore("§8").setScore(8);
        obj.getScore("§7").setScore(7);
        obj.getScore("§6").setScore(6);
        obj.getScore("§5").setScore(5);
        obj.getScore("§4").setScore(4);
        obj.getScore("§3").setScore(3);
        obj.getScore("§2").setScore(2);
        obj.getScore("§1").setScore(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                Objects.requireNonNull(scoreboard.getTeam("l12")).setSuffix(String.valueOf(ticks)); // Updated l12 value
                Objects.requireNonNull(scoreboard.getTeam("l11")).setSuffix(String.valueOf(ticks)); // Updated l11 value
                Objects.requireNonNull(scoreboard.getTeam("l10")).setSuffix(String.valueOf(ticks)); // Updated l10 value
                Objects.requireNonNull(scoreboard.getTeam("l9")).setSuffix(String.valueOf(ticks)); // Updated l9 value
                Objects.requireNonNull(scoreboard.getTeam("l8")).setSuffix(String.valueOf(ticks)); // Updated l8 value
                Objects.requireNonNull(scoreboard.getTeam("l7")).setSuffix(String.valueOf(ticks)); // Updated l7 value
                Objects.requireNonNull(scoreboard.getTeam("l6")).setSuffix(String.valueOf(ticks)); // Updated l6 value
                Objects.requireNonNull(scoreboard.getTeam("l5")).setSuffix(String.valueOf(ticks)); // Updated l5 value
                Objects.requireNonNull(scoreboard.getTeam("l4")).setSuffix(String.valueOf(ticks)); // Updated l4 value
                Objects.requireNonNull(scoreboard.getTeam("l3")).setSuffix(String.valueOf(ticks)); // Updated l3 value
                Objects.requireNonNull(scoreboard.getTeam("l2")).setSuffix(String.valueOf(ticks)); // Updated l2 value
                Objects.requireNonNull(scoreboard.getTeam("l1")).setSuffix(String.valueOf(ticks)); // Updated l1 value
            }
        }.runTaskTimer(getInstance(), 0, 1);
        player.setScoreboard(scoreboard);

    }

    @EventHandler
    public static void disablePortals(PlayerPortalEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL || event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            Bukkit.broadcastMessage("§4§LGOD DISAPPROVES OF THIS BEHAVIOUR");
            player.getWorld().strikeLightning(player.getLocation());
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 25, 25);
            }
        }
    }
}
