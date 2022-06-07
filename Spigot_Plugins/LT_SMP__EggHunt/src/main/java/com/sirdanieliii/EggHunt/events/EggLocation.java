package com.sirdanieliii.EggHunt.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import static com.sirdanieliii.EggHunt.other.Utilities.itemIsEgg;
import static com.sirdanieliii.EggHunt.configuration.ConfigManager.eggLocationLast;
import static com.sirdanieliii.EggHunt.other.Utilities.locationToString;

public class EggLocation implements Listener {
    @EventHandler
    public void eggBlockDrop(BlockDropItemEvent e) {

    }

    @EventHandler
    public void eggDropItem(PlayerDropItemEvent e) {
        try {
            ItemStack item = e.getItemDrop().getItemStack();
            if (itemIsEgg(item)) {
                eggLocationLast = locationToString(e.getItemDrop().getLocation());
            }
        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void eggBlockPlace(BlockPlaceEvent e) {
        Bukkit.broadcastMessage(e.getBlockPlaced().toString());
    }
}
