package com.sirdanieliii.EggHunt.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Events implements Listener {

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
