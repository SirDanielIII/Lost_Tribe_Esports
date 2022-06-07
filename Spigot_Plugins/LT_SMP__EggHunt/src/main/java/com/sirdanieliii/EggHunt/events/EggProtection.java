package com.sirdanieliii.EggHunt.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class EggProtection implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void dropEggWhenQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        for (int i = 0; i < player.getInventory().getSize(); i++) { // Loops through player's inventory
            ItemStack item = player.getInventory().getItem(i);
            try {
                if (Objects.requireNonNull(item).getType().equals(Material.ITEM_FRAME) && Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).hasCustomModelData())
                    if (Objects.requireNonNull(item.getItemMeta()).getCustomModelData() == 1) {
                        player.getWorld().dropItemNaturally(player.getLocation(), item);  // Drop Egg onto ground
                        player.getInventory().setItem(i, null);  // Remove Egg from inventory slot
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendTitle("§C§LTHE EGG HAS BEEN DROPPED", "§A" + player.getLocation(), 20, 70, 20);
                        }
                    }
            } catch (NullPointerException ignored) {
            }
        }
    }

    @EventHandler
    public void eggInHopper(InventoryPickupItemEvent e) {  // Prevent item from being thrown into hoppers
        ItemStack item = e.getItem().getItemStack();
        if (Objects.requireNonNull(item).getType().equals(Material.ITEM_FRAME) && Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).hasCustomModelData())
            if (Objects.requireNonNull(item.getItemMeta()).getCustomModelData() == 1) e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplode(EntityExplodeEvent event) {  // Prevent Egg block from being destroyed in explosions
        if (event.getEntity() instanceof Creeper || event.getEntity() instanceof TNTPrimed || event.getEntity() instanceof TNT)
            event.blockList().removeIf(block -> block.getType() == Material.GLASS || block.getType() == Material.ITEM_FRAME || block.getType() == Material.ARMOR_STAND);
    }

    @EventHandler
    private void onEventExplosion(EntityDamageEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            try {
                Material type = ((Item) e.getEntity()).getItemStack().getType();
                if (Objects.equals(type, Material.ITEM_FRAME)) { // Filter out Egg (which is an item frame)
                    e.setCancelled(true);
                }
            } catch (ClassCastException ignored) {
            }
        }
    }

    @EventHandler
    public void eggRenameItem(InventoryClickEvent event) {
        if (event.getView().getType() == InventoryType.ANVIL) {
            if (event.getRawSlot() == 2) {
                try {
                    if (!Objects.requireNonNull(Objects.requireNonNull(event.getInventory().getItem(0)).getItemMeta()).getDisplayName().equals(
                            Objects.requireNonNull(Objects.requireNonNull(event.getInventory().getItem(2)).getItemMeta()).getDisplayName())) {
                        event.setCancelled(true);
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
    }

    @EventHandler
    public void eggShiftClick(InventoryClickEvent event) {  // Prevent Egg from being shift clicked into another inventory
        if (event.getClick().isShiftClick()) {
            Inventory clicked = event.getClickedInventory();
            if (clicked == event.getWhoClicked().getInventory()) {
                try {
                    ItemStack clickedOn = event.getCurrentItem();
                    if (clickedOn != null) {
                        if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).hasCustomModelData()) {
                            if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getCustomModelData() == 1) event.setCancelled(true);
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
    }

    @EventHandler
    public void eggClickSlot(InventoryClickEvent event) {  // Prevent the egg from being clicked into another inventory
        Inventory clicked = event.getClickedInventory();
        // Hotbar Swap
        if (event.getAction().equals(InventoryAction.HOTBAR_SWAP)) {
            try {
                ItemStack item = event.getClick() == ClickType.NUMBER_KEY ?
                        event.getWhoClicked().getInventory().getItem(event.getHotbarButton()) : event.getCurrentItem();
                assert item != null;
                if (Objects.requireNonNull(item.getItemMeta()).hasCustomModelData())
                    if (Objects.requireNonNull(item.getItemMeta()).getCustomModelData() == 1) event.setCancelled(true);
            } catch (NullPointerException ignored) {
            }
        }
        // Initial Inventory != Destination Inventory
        if (clicked != event.getWhoClicked().getInventory()) {
            try {
                ItemStack onCursor = event.getCursor();
                assert onCursor != null;
                if (Objects.requireNonNull(onCursor.getItemMeta()).hasCustomModelData())
                    if (Objects.requireNonNull(onCursor.getItemMeta()).getCustomModelData() == 1) event.setCancelled(true);
            } catch (NullPointerException ignored) {
            }
        }
    }

    @EventHandler
    public void eggDragSlots(InventoryDragEvent event) {  // Prevent Egg from being dragged into a chest / hopper
        ItemStack dragged = event.getOldCursor(); // This is the item that is being dragged
        if (Objects.requireNonNull(dragged.getItemMeta()).hasCustomModelData()) {
            if (Objects.requireNonNull(dragged.getItemMeta()).getCustomModelData() == 1) {
                int inventorySize = event.getInventory().getSize(); // The size of the inventory, for reference
                // Now we go through all the slots and check if the slot is inside our inventory (using the inventory size as reference)
                for (int i : event.getRawSlots()) {
                    if (i < inventorySize) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }
}
